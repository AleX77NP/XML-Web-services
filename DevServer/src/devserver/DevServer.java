/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devserver;

import java.io.IOException;
import org.apache.xmlrpc.WebServer;
public class DevServer {

    private final static int port = 4413;
    
    public static void main(String[] args) {
        try {
            startServer(port);
        } catch(IOException ioe) {
            System.out.println("Server error: " + ioe.getMessage());
        }
    }
    
    public static void startServer(int port) throws IOException {
        //Start servera
        System.out.println("Dev server starting at port " + port + "...");
        WebServer server = new WebServer(port);
        
        //Registracija rukovodioca
        DevHandler dev = new DevHandler();
        server.addHandler("dev",dev);
        server.start();
        System.out.println("Ready to accept requests...");
    }
    
}
