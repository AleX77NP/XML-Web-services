/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devclient;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start extends JFrame implements ActionListener {
    
    JLabel slika;
    JPanel panel;
    JButton log,reg;
    
    Start() {
        try {
        ImageIcon i = new ImageIcon(ImageIO.read(this.getClass().getResource("imgpackage/homeimg.png")));
        slika = new JLabel(i);
        slika.setAlignmentX(Component.CENTER_ALIGNMENT);
        } catch(IOException e) {
            e.printStackTrace();
        }
        JLabel l = new JLabel(" ");
        JLabel l2 = new JLabel(" ");
        log = new JButton("Prijavite se");
        log.setAlignmentX(Component.CENTER_ALIGNMENT);
        log.setMaximumSize(new Dimension(160,35));
        log.setBackground(Color.RED);
        log.setOpaque(true);
        log.setBorderPainted(false);
        log.setForeground(Color.WHITE);
        reg = new JButton("Registrujte se");
        reg.setAlignmentX(Component.CENTER_ALIGNMENT);
        reg.setMaximumSize(new Dimension(160,35));
        reg.setBackground(Color.BLUE);
        reg.setOpaque(true);
        reg.setBorderPainted(false);
        reg.setForeground(Color.WHITE);
        
        log.addActionListener(this);
        reg.addActionListener(this);
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(slika);
        panel.add(l);
        panel.add(log);
        panel.add(l2);
        panel.add(reg);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        //pack();
        setTitle("Dobrodosli !");
        setSize(400, 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == log) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        } else {
            Signup su = new Signup();
            su.setVisible(true);
            this.dispose();
        }
    }
    
}
