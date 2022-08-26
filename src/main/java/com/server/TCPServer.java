package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        String fromClient;
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            DataOutputStream out = new DataOutputStream(
                    socket.getOutputStream()
            );
            fromClient = in.readLine();
            out.writeBytes(String.format("I got: %s%n", fromClient));
            socket.close();
        }
    }
}
