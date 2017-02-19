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
public class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("\nClientHandler Started for " + 
            this.socket);
        handleRequest(this.socket);
        System.out.println("ClientHandler Terminated for " 
            + this.socket + "\n");
    }
    public void handleRequest(Socket socket) {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));) {
            String headerLine = in.readLine();
            StringTokenizer tokenizer = 
                new StringTokenizer(headerLine);
            String httpMethod = tokenizer.nextToken();
      
            if (httpMethod.equals("GET")) {
                System.out.println("Get method processed");
                String httpQueryString = tokenizer.nextToken();
                StringBuilder responseBuffer = new StringBuilder();
                
                //this location may be different for you
                Scanner fileScanner = new Scanner(new File("src/l04.getandpost/diary.txt"));
                while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        responseBuffer.append(line);
                        }
                sendResponse(socket, 200, responseBuffer.toString());
            } else if (httpMethod.equals("POST")){
                Scanner diary = new Scanner(System.in);
                System.out.println("POST method prcoessed, Enter your diary entry");
                FileWriter fw = new FileWriter("diary.txt", true);
                fw.write(diary.next());
          
                 }
            else {
                 System.out.println("The HTTP method is not recognized");
                 sendResponse(socket, 405, "Method Not Allowed");
                }
            
            } catch (Exception e) {
                    e.printStackTrace();
             }
        
            }
    public void sendResponse(Socket socket, 
        int statusCode, String responseString) {
        String statusLine;
        String serverHeader = "Server: WebServer\r\n";
        String contentTypeHeader = "Content-Type: text/html\r\n";

        try (DataOutputStream out = 
                new DataOutputStream(socket.getOutputStream());) {
            if (statusCode == 200) {
        statusLine = "HTTP/1.0 200 OK" + "\r\n";
        String contentLengthHeader = "Content-Length: " 
            + responseString.length() + "\r\n";

        out.writeBytes(statusLine);
        out.writeBytes(serverHeader);
        out.writeBytes(contentTypeHeader);
        out.writeBytes(contentLengthHeader);
        out.writeBytes("\r\n");
        out.writeBytes(responseString);
             } else if (statusCode == 405) {
                statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
                out.writeBytes(statusLine);
                out.writeBytes("\r\n");
            } else {
                statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
                out.writeBytes(statusLine);
                out.writeBytes("\r\n");
         }
                out.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
    }
    
    

}
