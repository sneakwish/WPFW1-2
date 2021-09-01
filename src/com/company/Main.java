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
            s = in.readLine();
                System.out.println(s);
            OutputStream out = client.getOutputStream();
            String world = "<a href=\"https://www.google.nl\">World!</a>";
            String about = "Lees de<a href=\"/about\">About</a> Pagina.";
            String counter = "\n\rLees de<a href=\"/counter\"> Counter</a> Pagina.";
            if(s.equals("GET / HTTP/1.1")){
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "Hello " + world + about + counter).getBytes());
            }
            else if (s.equals("GET /about HTTP/1.1")){
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "ABOUT PAGINA " + world).getBytes());
            }

            else if (s.equals("GET /counter HTTP/1.1")){
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "ABOUT PAGINA " + world).getBytes());
            }

            else{
                out.write(("FAiled").getBytes());
            }

            in.close();
            out.close();
            client.close();
        }
    }
}
