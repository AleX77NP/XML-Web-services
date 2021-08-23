/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devclient;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

/**
 *
 * @author aleksandarmilanovic
 */
public class Home extends JFrame {
    JPanel panel;
    JLabel l;
    JTextArea area;
    JTextField f1, f2;
    JLabel l1,l2,l3;
    
    Vector<String> jezici = new Vector<String>();
    
    public Vector getLanguages() throws IOException, XmlRpcException {
        XmlRpcClient client = new XmlRpcClient("http://localhost:4413");
        Vector params = new Vector();
        Vector response = (Vector)client.execute("dev.getAllLanguages",params);
        return response;
    }
    
    Home() throws IOException, XmlRpcException {
        jezici = getLanguages();
        System.out.println(jezici.size());
        
        panel = new JPanel();
        
        List <String> lista = jezici.stream().collect(Collectors.toList());
        
        for(String obj: lista) {
            String slika = obj.split("#")[2];
            System.out.println(slika);
            try {
                ImageIcon i = new ImageIcon(ImageIO.read(this.getClass().getResource(slika)));
                JLabel ic = new JLabel(i);
                ic.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showDetails(obj.split("#")[0],obj.split("#")[1],obj.split("#")[3]);
                }
            });
                
                panel.add(ic);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        
        l1 = new JLabel("Naziv tehnologije: ");
        l2 = new JLabel("Kompanija/Licensa: ");
        l3 = new JLabel("Opis: ");
        
        l1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        l2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        l3.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        area = new JTextArea(8,24);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        f1 = new JTextField(10);
        f1.setEditable(false);
        f2 = new JTextField(10);
        f2.setEditable(false);
        p2.add(l1);
        p2.add(f1);
        p2.add(l2);
        p2.add(f2);
        p2.add(l3);
        p2.add(area);
        p2.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        
        add(panel, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
        
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tehnologije Web razvoja!");
    }

    protected void showDetails(String name, String company, String desc) {
        f1.setText(name);
        f2.setText(company);
        area.setText(desc);
    }
    
}
