package code;

import code.Users.Admin;
import code.Users.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose one of the following options");

        while (true) {
            System.out.println("1: Login and proceed");
            System.out.println("2: Register");
            System.out.println("0: Exit");
            int input = sc.nextInt();
            if (input == 0) {
                System.exit(0);
            } else if (input == 1) {
                int typeOfUser = Login.start();
                if (typeOfUser == -2) {
                    Admin.adminStart();
                } else if (typeOfUser == -1) {
                    System.out.println("No user found");
                } else {
                    Customer currentUser = Customer.allCustomer.get(typeOfUser);
                    currentUser.customerStart();
                }
            } else if (input == 2) {
                System.out.println("Register");
                // Register.start()
            }
        }
    }

    public static void loadData() {
        String filePath = "./data/customer.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                String customerId = row[0];
                String customerName = row[1];
                String customerPassword = row[2];
                String customerPhone = row[3];
                String customerEmail = row[4];
                String customerAddress = row[5];
                double customerMoneySpent = Double.parseDouble(row[6]);
                Customer customer = new Customer(customerId, customerName, customerPassword, customerPhone,
                        customerEmail, customerAddress, customerMoneySpent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}