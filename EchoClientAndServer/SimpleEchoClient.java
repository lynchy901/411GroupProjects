package simpleclientserverechoapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class SimpleEchoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            try (
                    Socket clientSocket = new Socket(localAddress, 6000);
                    PrintWriter out = new PrintWriter (clientSocket.getOutputStream(),true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                )
            {
                
                System.out.println("Connected to Server!");
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Enter text: ");
                    String inputLine = scanner.nextLine();
                    out.println(inputLine);
                    String response = br.readLine();
                    System.out.println("Server response: " + response);
                }
            } 
            
        }   catch (IOException ex) {
                ex.printStackTrace();
        }
    }
}
