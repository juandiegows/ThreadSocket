/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.areandina.threadsocket;

/**
 *
 * @author mejia
 */
public class ThreadSocket {

    public static void main(String[] args) {
        ThreadSocket.iniciarServidor();
          ThreadSocket.iniciarCliente();
    }

    public static void iniciarServidor() {
        Thread serverThread = new Thread(() -> Server.main(null));
        serverThread.start();
    }

    public static void iniciarCliente() {
        Thread clientThread = new Thread(() -> Client.main(null));
        clientThread.start();
    }
}
