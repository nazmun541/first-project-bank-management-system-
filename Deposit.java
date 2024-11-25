/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {
    
    JTextField t1;
    JButton b1, b2;
    JLabel l1;
    String pin;

    Deposit(String pin) {
        this.pin = pin;

        // Image setup
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 960, 1080);
        add(background);

        // Label setup
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        // TextField setup
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));

        // Button setup
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");

        // Layout setup
        setLayout(null);
        
        l1.setBounds(190, 350, 400, 35);
        background.add(l1);

        t1.setBounds(190, 420, 320, 25);
        background.add(t1);

        b1.setBounds(390, 588, 150, 35);
        background.add(b1);

        b2.setBounds(390, 633, 150, 35);
        background.add(b2);

        // Add action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);

        // JFrame setup
        setSize(960, 1080);
        setUndecorated(true);
        setLocation(500, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = t1.getText();
            Date date = new Date();

            if (ae.getSource() == b1) {
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit.");
                } else {
                    // Validation for numeric input
                    try {
                        double depositAmount = Double.parseDouble(amount);
                        if (depositAmount <= 0) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid amount greater than zero.");
                        } else {
                            Conn c1 = new Conn();
                            // Using parameterized queries to prevent SQL injection
                            String query = "INSERT INTO bank (pin, date, transaction_type, amount) VALUES (?, ?, 'Deposit', ?)";
                            PreparedStatement pstmt = c1.s.prepareStatement(query);
                            pstmt.setString(1, pin);
                            pstmt.setString(2, date.toString());
                            pstmt.setDouble(3, depositAmount);
                            pstmt.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Rs. " + depositAmount + " Deposited Successfully");
                            setVisible(false);
                            new Transactions(pin).setVisible(true);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                    }
                }
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("").setVisible(true);
    }
}
