package edu.auburn;

import java.io.PrintWriter;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PriceServer {
    public static void main(String[] args) {
        int port = 1000;
        try {
            ServerSocket server = new ServerSocket(port);
            Socket pipe = server.accept();
            PrintWriter out = new PrintWriter(pipe.getOutputStream(), true);
            Scanner in = new Scanner(pipe.getInputStream());

            while (true) {
                int id = in.nextInt();

                System.out.println("Requested of product with id = " + id);

                if (id != -1)
                    out.println(getPriceOf(id));
                else {
                    out.println(0);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double getPriceOf(int id) {
        Connection conn = null;
        double price = -1.0;
        try {
            String url = "jdbc:sqlite:" + "C:\\Users\\wbian\\Desktop\\SoftwareModeling\\Activity7\\Assignment3.db";
            conn = DriverManager.getConnection(url);

            String sql = "SELECT Price FROM Products WHERE ProductId = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                price = rs.getDouble("Price");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

}
