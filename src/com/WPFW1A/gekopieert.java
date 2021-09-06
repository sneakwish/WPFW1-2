package com.WPFW1A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class gekopieert {

    private static int counter = 0;
    private static int privatecounter = 0;
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String s;
        while (!(s = in.readLine()).equals(""))
            System.out.println(s);
        OutputStream out = client.getOutputStream();
        out.write("HTTP/1.0 200 OK\r\nContent-Type: text/plain\r\nContent-Length: 12\r\n\r\nHello world!".getBytes());
        in.close();
        out.close();
        client.close();
    }
}
