package edu.auburn;

public class CustomerModel {
    public int mCustomerID;
    public String mName, mNumber, mPaymentInfo;


    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append("\"").append(mCustomerID).append("\"").append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        sb.append("\"").append(mNumber).append("\"").append(",");
        sb.append("\"").append(mPaymentInfo).append("\"").append(")");
        return sb.toString();
    }
}
