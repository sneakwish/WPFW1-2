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
        String s;
        while(true) {
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            OutputStream out = client.getOutputStream();
            //reads User http req
            s = in.readLine();
            //checks for Index Link
            if(s.equals("GET / HTTP/1.1")) {
                out.write("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n<h3>Hello World Lees <a href='about'>About Page</a>!</h3>".getBytes());
            }

            //checks about thread
            if(s.equals("GET /about HTTP/1.1")) {
                out.write("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n<html><h1>About Page</h1></html>".getBytes());
            }

            //checks counter thread
            if(s.equals("GET /counter HTTP/1.1")) {
                counter++;
                String s1 = "HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n<center><h1>" + counter + "</h1></center>";
                out.write(s1.getBytes());
            }

            //checks if /add in thread
            if(s.contains("GET /add")){
                String stringAdd[] = s.split("=");
                stringAdd[1] = stringAdd[1].replace("&b", "");
                stringAdd[2] = stringAdd[2].replace(" HTTP/1.1" , "");
                int finalAdd = Integer.valueOf(stringAdd[1]) + Integer.valueOf(stringAdd[2]);

                String finalString = "HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n<h1>resultaat: " + stringAdd[1] + " + " + stringAdd[2] + " = " + finalAdd;
                out.write(finalString.getBytes());
            }


            //privatecounter thread
            if(s.equals("GET /privatecounter HTTP/1.1")) {
                privatecounter++;
                String s1 = "HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n<a href='privatecounter'>tel op</a>" + privatecounter;
                out.write(s1.getBytes());
            }

            System.out.println(s);
//            in.close();
            out.close();
            client.close();
        }
    }

//        while (!(s = in.readLine()).equals("")) {
//            System.out.println(s);
//        }
}
