package edu.auburn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerController {

    public AddCustomerView view;
    public SQLiteDataAdapter adapter;

    public AddCustomerController(AddCustomerView view, SQLiteDataAdapter adapter)   {
        this.view = view;
        this.adapter = adapter;

        this.view.btnAdd.addActionListener(new AddButtonListener());
        this.view.btnCancel.addActionListener(new CancelButtonListener());

    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CustomerModel customer = new CustomerModel();

            String id = AddCustomerController.this.view.txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                customer.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            String name = AddCustomerController.this.view.txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer name cannot be empty!");
                return;
            }

            customer.mName = name;

            String number = AddCustomerController.this.view.txtNumber.getText();
            if (number.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer number cannot be empty!");
                return;
            }
            customer.mNumber = number;

            String payment = AddCustomerController.this.view.txtPaymentInfo.getText();
            if (payment.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer payment cannot be empty!");
                return;
            }

            customer.mPaymentInfo = payment;

            switch (adapter.saveCustomer(customer)) {
                case SQLiteDataAdapter.CUSTOMER_DUPLICATE_ERROR:
                    JOptionPane.showMessageDialog(null, "Customer NOT added successfully! Duplicate Customer ID!");
                default:
                    JOptionPane.showMessageDialog(null, "Customer added successfully!" + customer);
            }
        }
    }

    class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane.showMessageDialog(null, "You click on Cancel button!!!");
        }
    }

}