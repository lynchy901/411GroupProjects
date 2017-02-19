/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l04.getandpost;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author LeviPotutschnig
 */
public class HTTPClient {
    public HTTPClient() {
        System.out.println("HTTP Client Started");
        try {
            InetAddress serverInetAddress = 
               InetAddress.getByName("127.0.0.1");
            Socket connection = new Socket(serverInetAddress, 80);

            try (OutputStream out = connection.getOutputStream();
                 BufferedReader in = 
                     new BufferedReader(new 
                         InputStreamReader(
                             connection.getInputStream()))) {
                sendPost(out);
                sendGet(out);
                System.out.println(getResponse(in));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    private void sendGet(OutputStream out) {
        try {
            out.write("GET /default\r\n".getBytes());
            out.write("User-Agent: Mozilla/5.0\r\n".getBytes());
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void sendPost(OutputStream out){
        try{
            out.write("POST /default\r\n:".getBytes());
            out.write("User-Agent: Mozilla/5.0\r\n".getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private String getResponse(BufferedReader in) {
            try {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
                }
            return response.toString();
                } catch (IOException ex) {
                ex.printStackTrace();
            }
            return "";
        }
     
}


