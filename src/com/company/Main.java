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
            String html = "";
            out.write("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\nContent-Length: \r\n\r\nHello <a href=\"https://www.google.com/\">World!</a>".getBytes());

            in.close();
            out.close();
            client.close();
        }
    }
}
