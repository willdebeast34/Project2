package edu.auburn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PriceClientUI {
    public JFrame view;

    public JButton btnCheck = new JButton("Check Price");

    public JTextField txtPrice = new JTextField(20);

    Socket link;

    Scanner input;

    PrintWriter output;

    public PriceClientUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Check Price");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new FlowLayout());

        txtPrice.setText("Enter the product ID here");

        view.getContentPane().add(txtPrice);
        view.getContentPane().add(btnCheck);

        btnCheck.grabFocus();

        txtPrice.addFocusListener(new PriceFocusListener());

        btnCheck.addActionListener(new CheckActionListener());

        try {
            link = new Socket("localhost", 1000);
            input = new Scanner(link.getInputStream());
            output = new PrintWriter(link.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {
        int port = 1000;
        PriceClientUI ui = new PriceClientUI();
        ui.view.setVisible(true);

    }

    private class PriceFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent focusEvent) {
            txtPrice.setText("");
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {

        }
    }

    private class CheckActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String s = txtPrice.getText();

            if (s.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }

            int id;

            try {
                id = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

            double price = getPriceFromServer(id);

            if (price != -1.0)
                txtPrice.setText(Double.toString(price));
            else
                JOptionPane.showMessageDialog(null, "Product not exists!");
        }

        private double getPriceFromServer(int id) {
            try {
                output.println(id);
                double p = input.nextDouble();
                System.out.println("Sent " + id + " received " + p);
                return p;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1.0;
        }
    }
}