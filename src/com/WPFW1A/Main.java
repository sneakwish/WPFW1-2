package com.WPFW1A;

import java.net.*;
import java.io.*;

public class Main {
    public static int teller = 0;
    public static int privateTeller = 0;

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        while(true) {
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String s;

            String world = "<a href=\"https://www.google.nl\">World!</a><br>";
            String about = "Lees de<a href=\"/about\">About</a> Pagina.<br>";
            String counter = "\n\rLees de<a href=\"/counter\"> Counter</a> Pagina.<br>";
            String add = "\n\rLees de<a href=\"/add?a=3&b=4\"> add?a=3&b=4</a> Pagina.<br>";
            String privatecounter = "\n\rLees de<a href=\"/privatecounter?parameter="+privateTeller+"\"> privatecounter</a> Pagina.<br>";
            String text = "U gebruikt: ";

            OutputStream out = client.getOutputStream();

            String regel = in.readLine();
            System.out.println("Dit is de eerste regel:\n\r" + regel);

            String[] browser = {};
            while (!(s = in.readLine()).equals("")) {
                System.out.println(s);

                if(s.startsWith("User-Agent")){
                    browser = s.split(":");
                }
            }

            if (regel.contains("GET / HTTP/1.1")) {
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "Hello " + world + about + counter + add + privatecounter + browser[1]).getBytes());
            } else if (regel.contains("GET /about HTTP/1.1")) {
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "ABOUT PAGINA ").getBytes());
            } else if (regel.contains("GET /counter HTTP/1.1")) {
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "Counter PAGINA: " + (teller = teller + 1)).getBytes());
            } else if (regel.contains("GET /add?a=3&b=4 HTTP/1.1")) {
                String three = regel.substring(11, 12);
                String four = regel.substring(15, 16);
                int parseThree = Integer.parseInt(three);
                int parseFour = Integer.parseInt(four);
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "3+4 PAGINA: " + (parseFour + parseThree)).getBytes());
            } else if (regel.contains("GET /privatecounter?parameter="+privateTeller+" HTTP/1.1")) {
                out.write(("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: \r\n\r\n" +
                        "De teller staat op " + privateTeller +", <a href= /privatecounter?parameter=" + (privateTeller = privateTeller + 1) + ">hier</a> hier om te verhogen: ").getBytes());
            }

//                else 404 error page
                else{
                    out.write(("HTTP/1.0 404 Not Found\r\n" +
                            "Content-Type: text/html\r\n" +
                            "Content-Length: \r\n\r\n" +
                            "<h1>Error 404: Page Not Found</h1> ").getBytes());
                }

            in.close();
            out.close();
            client.close();
        }
    }
}
