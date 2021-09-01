package com.company;

import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        while(true) {
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String s;
            while (!(s = in.readLine()).equals(""))
                System.out.println(s);
            OutputStream out = client.getOutputStream();
            String world = "<a href=\"https://www.google.nl\">World!</a>";
            String about = "<a href=\"/about\">About!</a>";
            if(s.equals("HTTP /about"))
            out.write(("HTTP/1.0 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: \r\n\r\n" +
                    "Hello " + world + about).getBytes());

            in.close();
            out.close();
            client.close();
        }
    }
}
