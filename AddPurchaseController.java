package edu.auburn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPurchaseController {

    public AddPurchaseView view;
    public SQLiteDataAdapter adapter;

    public AddPurchaseController(AddPurchaseView view, SQLiteDataAdapter adapter)   {
        this.view = view;
        this.adapter = adapter;

        this.view.btnAdd.addActionListener(new AddButtonListener());
        this.view.btnCancel.addActionListener(new CancelButtonListener());

    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();

            String id = AddPurchaseController.this.view.txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            String name = AddPurchaseController.this.view.txtProductID.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Purchase name cannot be empty!");
                return;
            }

            purchase.mPurchaseID = Integer.parseInt(name);

            String  number = AddPurchaseController.this.view.txtCustomerID.getText();
            if (number.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer number cannot be empty!");
                return;
            }

            String payment = AddPurchaseController.this.view.txtQuantity.getText();
            if (payment.length() == 0) {
                JOptionPane.showMessageDialog(null, "Quantity cannot be empty!");
                return;
            }

            switch (adapter.savePurchase(purchase)) {
                case SQLiteDataAdapter.PURCHASE_DUPLICATE_ERROR:
                    JOptionPane.showMessageDialog(null, "Customer NOT added successfully! Duplicate Customer ID!");
                default:
                    JOptionPane.showMessageDialog(null, "Customer added successfully!" + purchase);
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