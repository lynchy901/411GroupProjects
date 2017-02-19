/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getandpost;
import java.net.*;
import java.io.*;
/**
 *
 * @author LeviPotutschnig
 */
public class Webserver {

    public Webserver() {
        System.out.println("Webserver Started");
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            while (true) {
                System.out.println("Waiting for client request");
                Socket remote = serverSocket.accept();
                System.out.println("Connection made");
                new Thread(new ClientHandler(remote)).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
}
