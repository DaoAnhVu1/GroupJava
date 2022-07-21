import Users.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import Products.Product;

public class Main {
    public static void main(String[] args) {
        loadProductData("./data/Console.csv");
        loadProductData("./data/CPU.csv");
        loadProductData("./data/GPU.csv");
        loadMemberData();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose one of the following options");

        while (true) {
            System.out.println();
            System.out.println("1: Login and proceed");
            System.out.println("2: Register");
            System.out.println("3: Show all items and details");
            System.out.println("4: Show all items by category");
            System.out.println("5: Show all items by price");
            System.out.println("0: Exit");
            System.out.println();
            int input = sc.nextInt();
            System.out.println();
            if (input == 0) {
                System.exit(0);
            } else if (input == 1) {
                int typeOfUser = Login.start();
                if (typeOfUser == -2) {
                    Admin.adminStart();
                } else if (typeOfUser == -1) {
                    System.out.println("No user found");
                } else {
                    Member currentUser = Member.allMember.get(typeOfUser);
                    currentUser.memberStart();
                }
            } else if (input == 2) {
                Register.start();
            } else if (input == 3) {
                Guess.showAllItemsDetails();
            } else if (input == 4) {
                Guess.showAllItemsCategory();
            } else if (input == 5) {
                Guess.showAllItemsByPrice();
            }
        }
    }

    public static void loadMemberData() {
        String filePath = "./data/member.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                String memberId = row[0];
                String memberName = row[1];
                String memberPassword = row[2];
                String memberPhone = row[3];
                String memberEmail = row[4];
                String memberAddress = row[5];
                double memberMoneySpent = Double.parseDouble(row[6]);
                Member member = new Member(memberId, memberName, memberPassword, memberPhone,
                        memberEmail, memberAddress, memberMoneySpent);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadProductData(String filePath) {
        String fullFileName = filePath.substring(7);
        String category = fullFileName.split("\\.")[0];
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                String productId = row[0];
                String productName = row[1];
                Double productPrice = Double.parseDouble(row[2]);
                String productCategory = category;
                int productQuantity = Integer.parseInt(row[3]);

                Product product = new Product(productId, productName, productPrice, productCategory, productQuantity);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}