package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer extends Thread {
    protected Socket socket = null;
    protected static ArrayList<ChatServer> threadList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            ChatServer serverThread = new ChatServer(threadList, socket);
            threadList.add(serverThread);
            serverThread.start();
        }
    }

    public ChatServer(ArrayList<ChatServer> threadList, Socket socket) {
        this.threadList = threadList;
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void run(){
        String fromClient;
        try {
            while (true) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                fromClient = in.readLine();
                sendMsg(fromClient);
            }
        } catch (IOException e) {
            System.out.printf("Error occurred: %s", e.getStackTrace());
        }
    }

    public void sendMsg(String message) {
        for (ChatServer cs : threadList) {
            Socket socket = cs.getSocket();
            try {
                DataOutputStream out = new DataOutputStream(
                        socket.getOutputStream()
                );
                out.writeBytes(String.format("I got: %s%n", message));
            } catch (IOException e) {
                System.out.printf("Error occurred: %s", e.getStackTrace());
            }

        }
    }
}
