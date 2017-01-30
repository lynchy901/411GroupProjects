package simpleclientserverechoapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author nathanlynch
 */
public class SimpleEchoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client!");
            
            try (
                    BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ) 
            {
                
                String inputLine;
                
                while ((inputLine = br.readLine()) != null) {
                    System.out.println("Server " + inputLine);
                    out.println(inputLine);
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
    }
}

