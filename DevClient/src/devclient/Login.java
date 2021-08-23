/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;



public class Login extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Vector response = login();
            if(response.size() > 1) {
                
                Home h = new Home();
                h.setVisible(true);
                this.dispose();
                
                System.out.println(response.get(0).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Uneti podaci nisu korektni.");
            }
            
        } catch(IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage());
            ioe.printStackTrace();
        }
        catch(XmlRpcException xre) {
            System.out.println("XmlRpc Exception: " + xre.getMessage());
            xre.printStackTrace();
          }
    }
    
    JPanel panel;
    JLabel usernameL, passwordL;
    JTextField userTf;
    JPasswordField passTf;
    JButton loginB;
    
    
    Login() {
        usernameL = new JLabel();
        usernameL.setText("Unesite korisnicko ime: ");
        passwordL = new JLabel();
        passwordL.setText("Unesite lozinku: ");
        userTf = new JTextField();
        passTf = new JPasswordField();
        
        loginB = new JButton("Prijavite se");
        loginB.setBackground(Color.RED);
        loginB.setForeground(Color.WHITE);
        loginB.setOpaque(true);
        loginB.setBorderPainted(false);
        loginB.addActionListener(this);
        
        panel = new JPanel(new GridLayout(9,1));
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        
        JLabel l = new JLabel("");
        
        panel.add(usernameL);
        panel.add(userTf);
        panel.add(passwordL);
        panel.add(passTf);
        panel.add(l);
        panel.add(loginB);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setTitle("Ulogujte se ovde !");
        setSize(450, 350);
    }
    
    
    
    public Vector login() throws IOException, XmlRpcException {
        XmlRpcClient client = new XmlRpcClient("http://localhost:4413");
        Vector params = new Vector();
        params.add(userTf.getText());
        params.add(String.valueOf(passTf.getPassword()));
        Vector response = (Vector)client.execute("dev.login",params);
        return response;
    }
    
}
