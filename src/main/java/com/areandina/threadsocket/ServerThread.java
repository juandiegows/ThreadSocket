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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mejia
 */
public class ServerThread extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int desaciertos = 0;

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

                int randomNumber =  Util.GenerarRandom();
                int guess = Integer.parseInt(inputLine.trim());
                System.out.println("Servidor: #"+randomNumber   );
                if (guess == randomNumber) {
                    out.println("Adivinaste!");
                    desaciertos = 0; 
                } else {
                 
                    desaciertos++;
                    if (desaciertos >= Server.MAX_DESACIERTOS) {
                        out.println("Perdiste!");
                        break;
                    }else{
                           out.println("Fallaste!");
                    }
                }
            }

      
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            
        } 
    }
}
