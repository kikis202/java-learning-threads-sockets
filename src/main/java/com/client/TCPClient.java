package com.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
        DataOutputStream out = new DataOutputStream(
                socket.getOutputStream()
        );
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        out.writeBytes("Hello!\n");
        System.out.println(in.readLine());
        socket.close();
    }
}
