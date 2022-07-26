import Order.Order;
import Products.Product;
import Users.Admin;
import Users.Guest;
import Users.Member;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Store Data
        loadProductData("./data/Console.csv");
        loadProductData("./data/CPU.csv");
        loadProductData("./data/GPU.csv");
        loadProductData("./data/HardDrive.csv");
        loadProductData("./data/headphones.csv");
        loadProductData("./data/keyboard.csv");
        loadMemberData();
        loadOrderData();
        Scanner sc = new Scanner(System.in);
        System.out.println();

        //The welcome screen as in the requirements
        System.out.println("COSC2081 GROUP ASSIGNMENT"
                + "\nSTORE ORDER MANAGEMENT SYSTEM"
                + "\nInstructor: Mr. Minh Vu"
                + "\nGroup: Group 14"
                + "\ns3925921, Nguyen Dinh Khai"
                + "\ns3926187, Dao Anh Vu"
                + "\ns3925982, Bui Khac Tien"
                + "\ns3926873, Truong Hoang Tuan Kiet");
        System.out.println();
        System.out.println("Please choose one of the following options");

        //The store selection function
        while (true) {
            try {
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
                    Guest.showAllItemsDetails();
                } else if (input == 4) {
                    Guest.showAllItemsCategory();
                } else if (input == 5) {
                    Guest.showAllItemsByPrice();
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                sc.nextLine();
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
                if (line.contains("ï»¿")) {
                    line = line.replace("ï»¿", "");
                }
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
                if (line.contains("ï»¿")) {
                    line = line.replace("ï»¿", "");
                }
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

    public static void loadOrderData() {
        String filePath = "./data/Order.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                if (line.contains("ï»¿")) {
                    line = line.replace("ï»¿", "");
                }
                String[] row = line.split(",");
                String orderId = row[0];
                String memberId = row[1];
                String orderDate = row[2];
                double total = Double.parseDouble(row[3]);
                String status = row[4];
                HashMap<Product, Integer> cart = new HashMap<>();

                for (int i = 5; i < row.length; i++) {
                    String[] data = row[i].split(":");
                    for (String category : Product.categoryList) {
                        boolean found = false;
                        for (Product product : Product.productMap.get(category)) {
                            if (product.getProductId().equals(data[0])) {
                                cart.put(product, Integer.parseInt(data[1]));
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                }

                Order order = new Order(orderId, memberId, orderDate, total, status, cart);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}