package edu.auburn;

public interface IDataAdapter {

    public static final int CONNECTION_OPEN_OK = 100;
    public static final int CONNECTION_OPEN_FAILED = 101;

    public static final int CONNECTION_CLOSE_OK = 200;
    public static final int CONNECTION_CLOSE_FAILED = 201;


    public static final int PRODUCT_SAVE_OK = 0;
    public static final int PRODUCT_SAVE_FAILED = 1;
    public static final int PRODUCT_DUPLICATE_ERROR = 1;

    public static final int CUSTOMER_SAVED_OK = 0;
    public static final int CUSTOMER_SAVE_FAILED = 1;
    public static final int CUSTOMER_DUPLICATE_ERROR = 1;

    public static final int PURCHASE_SAVE_OK = 500;
    public static final int PURCHASE_SAVE_FAILED = 501;
    public static final int PURCHASE_DUPLICATE_ERROR = 1;


    public int connect(String dbfile);
    public int disconnect();

    public CustomerModel loadCustomer(int id);
    public int saveCustomer(CustomerModel model);

    public ProductModel loadProduct(int id);
    public int saveProduct(ProductModel model);



    public PurchaseModel loadPurchase(int id);
    public int savePurchase(PurchaseModel model);
    public UserModel loadUser(String username);

    public PurchaseHistoryModel loadPurchaseHistory(int customerID);

//
//    public int loadPurchase(int id);
//    public int savePurchase(PurchaseModel model);
}
