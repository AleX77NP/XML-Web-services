/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devclient;

import java.io.IOException;
import org.apache.xmlrpc.XmlRpcException;

public class DevClient {
    public static void main(String[] args) throws IOException, XmlRpcException {
        Start s = new Start();
        s.setVisible(true);
    }
    
}
