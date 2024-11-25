
package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    Login() {
        // Frame settings
        setTitle("Bank Management System - Login");
        setLayout(null);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel logo = new JLabel(i3);
        logo.setBounds(70, 10, 100, 100);
        add(logo);

        // Labels
        l1 = new JLabel("WELCOME TO BANK");
        l1.setFont(new Font("Oswald", Font.BOLD, 38));
        l1.setBounds(200, 20, 450, 40);
        add(l1);

        l2 = new JLabel("Card Number:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125, 150, 375, 30);
        add(l2);

        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125, 220, 375, 30);
        add(l3);

        // TextField for Card Number
        tf1 = new JTextField(15);
        tf1.setBounds(300, 150, 230, 30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);

        // PasswordField for PIN
        pf2 = new JPasswordField(15);
        pf2.setBounds(300, 220, 230, 30);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        add(pf2);

        // Buttons
        b1 = new JButton("SIGN IN");
        b1.setBounds(300, 300, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);

        b2 = new JButton("CLEAR");
        b2.setBounds(430, 300, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        add(b2);

        b3 = new JButton("SIGN UP");
        b3.setBounds(300, 350, 230, 30);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        add(b3);

        // Action Listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // Frame settings
        getContentPane().setBackground(Color.WHITE);
        setSize(800, 480);
        setLocation(350, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) { // SIGN IN
                String cardNumber = tf1.getText();
                String pin = pf2.getText();

                Conn c1 = new Conn();
                String query = "SELECT * FROM login WHERE card_number = ? AND pin = ?";
                PreparedStatement pstmt = c1.c.prepareStatement(query);
                pstmt.setString(1, cardNumber);
                pstmt.setString(2, pin);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (ae.getSource() == b2) { // CLEAR
                tf1.setText("");
                pf2.setText("");
            } else if (ae.getSource() == b3) { // SIGN UP
                setVisible(false);
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}

