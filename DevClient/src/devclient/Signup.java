/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devclient;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class Signup extends JFrame implements ActionListener {
      JPanel panel;
      JLabel usernameL, passwordL, namesurL;
      JTextField userTf, nameTf;
      JPasswordField passTf;
      JButton regB;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(nameTf.getText().length() < 5 || userTf.getText().length() < 5 || 
        String.valueOf(passTf.getPassword()).length() < 6) {
            JOptionPane.showMessageDialog(null, "Nevalidni podaci, ime i prezime, username i lozinka moraju imati najmanje 5,5, odnosno 6 karaktera.");
        } else {
        try {
            Vector response = signup();
            if(response.size() > 1) {
                JOptionPane.showMessageDialog(null, "Registrovani ste! Prijavite se.");
                Login l = new Login();
                l.setVisible(true);
                this.dispose();
                System.out.println(response.get(0).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Doslo je do greske. Mozda je username zauzet.");
                System.out.println("Doslo je do greske");
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
    }
    
    Signup() {
        namesurL = new JLabel();
        namesurL.setText("Unesite ime i prezime");
        usernameL = new JLabel();
        usernameL.setText("Unesite korisnicko ime: ");
        passwordL = new JLabel();
        passwordL.setText("Unesite lozinku: ");
        nameTf = new JTextField();
        userTf = new JTextField();
        passTf = new JPasswordField();
        
        regB = new JButton("Registrujte se");
        regB.setBackground(Color.BLUE);
        regB.setForeground(Color.WHITE);
        regB.setOpaque(true);
        regB.setBorderPainted(false);
        regB.addActionListener(this);
        regB.addActionListener(this);
        
        panel = new JPanel(new GridLayout(9,1));
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        
        JLabel l = new JLabel("");
        
        panel.add(namesurL);
        panel.add(nameTf);
        panel.add(usernameL);
        panel.add(userTf);
        panel.add(passwordL);
        panel.add(passTf);
        panel.add(l);
        panel.add(regB);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setTitle("Registrujte se ovde !");
        setSize(450, 350);
    }
    
    public Vector signup() throws XmlRpcException, IOException {
        XmlRpcClient client = new XmlRpcClient("http://localhost:4413");
        Vector params = new Vector();
        params.add(nameTf.getText());
        params.add(userTf.getText());
        params.add(String.valueOf(passTf.getPassword()));
        Vector response = (Vector)client.execute("dev.signup",params);
        return response;
    }
}
