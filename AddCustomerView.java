package edu.auburn;

import javax.swing.*;
import java.awt.*;

public class AddCustomerView extends JFrame {

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtNumber = new JTextField(20);
    public JTextField txtPaymentInfo = new JTextField(20);


    public AddCustomerView() {
        this.setTitle("Add Customer");
        this.setSize(600, 400);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        String[] labels = {"CustomerID ", "Name ", "Number ", "PaymentInfo "};

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID "));
        line1.add(txtCustomerID);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name "));
        line2.add(txtName);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Number "));
        line3.add(txtNumber);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Payment Info "));
        line4.add(txtPaymentInfo);
        this.getContentPane().add(line4);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAdd);
        panelButtons.add(btnCancel);
        this.getContentPane().add(panelButtons);

//Lay out the panel.
//        SpringUtilities.makeCompactGrid(p,
//                numPairs, 2, //rows, cols
//                6, 6,        //initX, initY
//                6, 6);       //xPad, yPad

    }

}

