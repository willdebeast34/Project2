package edu.auburn;

import java.net.Socket;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {
        int port = 1000;
        try {
            Socket link = new Socket("localhost", port);
            link.getOutputStream().write(5);
            link.getOutputStream().flush();

            Scanner in = new Scanner(link.getInputStream());

            double p = in.nextDouble();

            System.out.println(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
