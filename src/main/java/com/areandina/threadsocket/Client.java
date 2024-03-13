/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.areandina.threadsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author mejia
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int aciertos = 0;
        int desaciertos = 0;

        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("¡Bienvenido al juego de adivinar números!");
            System.out.println("Escribe 'terminar' para salir.");

            while (true) {
                int randomNumber = Util.GenerarRandom();
                System.out.println("Cliente: el número es  " + randomNumber);
                out.println(randomNumber);
                String response = in.readLine();
                System.out.println("Servidor: " + response);
                if (response.equalsIgnoreCase("Perdiste!")) {
                    break;
                }

                if (response.equalsIgnoreCase("Adivinaste!")) {
                    aciertos++;
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("------------------------adivinaste-----------------------------");
                    System.out.println("-----------------------------------------------------------------");

                } else if (response.equalsIgnoreCase("Fallaste!")) {
                    desaciertos++;
                }

                if (response.equalsIgnoreCase("terminar")) {
                    break;
                }
            }
            System.out.println("Aciertos: " + aciertos + ", Desaciertos: " + desaciertos);

            out.close();
            in.close();
            stdIn.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Aciertos: " + aciertos + ", Desaciertos: " + desaciertos);
        }
    }

}
