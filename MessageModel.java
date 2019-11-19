package edu.auburn;
public class MessageModel {
    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int GET_USER = 10;
    public static final int PUT_USER = 11;

    public static final int GET_PRODUCT = 100;
    public static final int PUT_PRODUCT = 101;

    public static final int GET_CUSTOMER = 200;
    public static final int PUT_CUSTOMER = 201;

    public static final int OPERATION_OK = 1000; // server responses!
    public static final int OPERATION_FAILED = 1001;
    public static final int GET_PURCHASE_LIST = 500;

    public int code;
    public int ssid;
    public String data;
}
