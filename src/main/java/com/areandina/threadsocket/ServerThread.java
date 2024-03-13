/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areandina.threadsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author mejia
 */
public class ServerThread extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int aciertos = 0;
    private int desaciertos = 0;
    private int desaciertosTotal = 0;

    public ServerThread(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("terminar")) {
                    break;
                }

                int randomNumber = (int) (Math.random() * 10);
                int guess = Integer.parseInt(inputLine.trim());

                if (guess == randomNumber) {
                    out.println("Adivinaste!");
                    aciertos++;
                    desaciertos = 0; // Reiniciar contador de desaciertos
                } else {
                    out.println("Fallaste!");
                    desaciertos++;
                    desaciertosTotal++;
                    if (desaciertos >= Server.MAX_DESACIERTOS) {
                        out.println("Perdiste!");
                        out.println("Aciertos: " + aciertos + ", Desaciertos: " + desaciertos);
                        break;
                    }
                }
            }

            out.println("Aciertos: " + aciertos + ", Desaciertos: " + desaciertos);
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            out.println("Aciertos: " + aciertos + ", Desaciertos: " + desaciertos);
        }
    }
}
