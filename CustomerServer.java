package edu.auburn;

import java.io.PrintWriter;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerServer {
    static String dbfile = "C:\\Users\\wbian\\Desktop\\SoftwareModeling\\Activity7\\Assignment3.db";

    public static void main(String[] args) {

        int port = 1000;

        if (args.length > 0) {
            System.out.println("Running arguments: ");
            for (String arg : args)
                System.out.println(arg);
            port = Integer.parseInt(args[0]);
            dbfile = args[1];
        }

        try {
            ServerSocket server = new ServerSocket(port);

            System.out.println("Server is listening at port = " + port);

            while (true) {
                Socket pipe = server.accept();
                PrintWriter out = new PrintWriter(pipe.getOutputStream(), true);
                Scanner in = new Scanner(pipe.getInputStream());

                String command = in.nextLine();
                if (command.equals("GET")) {
                    String str = in.nextLine();
                    System.out.println("GET customer with id = " + str);
                    int customerID = Integer.parseInt(str);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Customer WHERE CustomerID = " + customerID;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            out.println(rs.getString("Name")); // send back product name!
                            out.println(rs.getString("Number")); // send back product price!
                            out.println(rs.getString("Payment")); // send back product quantity!
                        }
                        else
                            out.println("null");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();
                }

                if (command.equals("PUT")) {
                    String id = in.nextLine();  // read all information from client
                    String name = in.nextLine();
                    String number = in.nextLine();
                    String payment = in.nextLine();

                    System.out.println("PUT command with CustomerID = " + id);

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection(url);

                        String sql = "SELECT * FROM Customer WHERE CustomerID = " + id;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            rs.close();
                            stmt.execute("DELETE FROM Customer WHERE CustomerID = " + id);
                        }

                        sql = "INSERT INTO Customer VALUES (" + id + ",\"" + name + "\",\""
                                + number + "\",\"" + payment + "\")";
                        System.out.println("SQL for PUT: " + sql);
                        stmt.execute(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();


                } else {
                    out.println(0); // logout unsuccessful!
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}