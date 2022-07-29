package Users;

import java.io.*;
import java.util.*;

import Products.Product;
import Order.Order;

public class Admin extends Guest {
    public static void adminStart() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Welcome ADMIN");
            System.out.println("Choose your actions: ");
            System.out.println("1: Show all users");
            System.out.println("2: Show all items and its details");
            System.out.println("3: Show all items by category");
            System.out.println("4: Show all items by price");
            System.out.println("5: Change product's price");
            System.out.println("6: Add new product");
            System.out.println("7: Remove product");
            System.out.println("8: Change order status");
            System.out.println("0: Logout");
            System.out.println();
            System.out.printf("Your input: ");
            int input = sc.nextInt();
            System.out.println();
            if (input == 0) {
                break;
            } else if (input == 1) {
                showAllUser();
            } else if (input == 2) {
                Guest.showAllItemsDetails();
            } else if (input == 3) {
                Guest.showAllItemsCategory();
            } else if (input == 4) {
                Guest.showAllItemsByPrice();
            } else if (input == 5) {
                Admin.changeProductPrice();
            } else if (input == 6) {
                Admin.addNewProduct();
            } else if (input == 7) {
                removeProduct();
            } else if (input == 8) {
                changeOrderStatus();
            }
        }
    }

    public static void showAllUser() {
        for (Member member : Member.allMember) {
            System.out.println(member.getMemberId());
            System.out.println(member.getMemberName());
            System.out.println(member.getMemberPassword());
            System.out.println();
        }
    }

    public static void changeProductPrice() {
        Scanner sc = new Scanner(System.in);
        int indexToShow = 1;
        for (String category : Product.categoryList) {
            System.out.println(indexToShow + ": " + category);
            indexToShow += 1;
        }

        try {
            System.out.print("Choose a category: ");
            int inputCategory = sc.nextInt();
            System.out.println();

            if (!(inputCategory > 0 && inputCategory <= Product.categoryList.size())) {
                System.out.println("Invalid input");
                return;
            }

            String choosenCategory = Product.categoryList.get(inputCategory - 1);
            ArrayList<Product> choosenList = Product.productMap.get(choosenCategory);
            indexToShow = 1;

            System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", "Number ", "Name", "Price", "Category", "Quantity");
            System.out.println();

            for (Product product : choosenList) {
                System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", indexToShow, product.getProductName(),
                        product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
                indexToShow += 1;
                System.out.println();
            }

            System.out.print("Choose a product: ");
            int indexChosenProduct = sc.nextInt();
            System.out.println();

            Product chosenProduct = choosenList.get(indexChosenProduct - 1);
            System.out.print("Enter the new price: ");
            double newPrice = sc.nextDouble();
            System.out.println();

            chosenProduct.setProductPrice(newPrice);

            try {
                FileWriter fw = new FileWriter("./data/" + choosenCategory + ".csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                for (Product product : choosenList) {
                    pw.println(product.getProductId() + "," + product.getProductName() + "," + product.getProductPrice()
                            + "," + product.getProductQuantity());
                }

                pw.flush();
                pw.close();
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }

            System.out.println("Success");
        } catch (Exception e) {
            System.out.println();
            System.out.println("Invalid Input");
        }

    }

    public static void addNewProduct() {
        try {
            Scanner sc = new Scanner(System.in);
            int indexToShow = 1;
            for (String category : Product.categoryList) {
                System.out.println(indexToShow + ": " + category);
                indexToShow += 1;
            }
            System.out.print("Choose a category: ");
            int inputCategory = sc.nextInt();
            sc.nextLine();
            System.out.println();
            String choosenCategory = Product.categoryList.get(inputCategory - 1);

            System.out.print("Enter the name: ");
            String productName = sc.nextLine();
            System.out.println();
            System.out.print("Enter the price: ");
            String productPrice = sc.nextLine();
            System.out.println();
            System.out.print("Enter the quantity: ");
            String productQuantity = sc.nextLine();
            System.out.println();

            String id = UUID.randomUUID().toString().substring(0, 8);

            try {
                FileWriter fw = new FileWriter("GroupJava/data/" + choosenCategory + ".csv", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(id + "," + productName + "," + productPrice + "," + productQuantity);
                pw.flush();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Product newProduct = new Product(id, productName, Double.parseDouble(productPrice), choosenCategory,
                    Integer.parseInt(productQuantity));
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public static void changeOrderStatus() {
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<Order> list = Order.allOrder;
            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s", "OrderId ", "MemberId", "Date", "Total", "Status",
                    "Product");
            System.out.println();
            for (Order order : list) {
                System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-15s", order.getOrderId(), order.getMemberId(),
                        order.getOrderDate(),
                        order.getTotal(), order.getStatus());
                for (Product product : order.getItems().keySet()) {
                    System.out.printf("|%-20s", product.getProductName());
                    System.out.print(" : " + order.getItems().get(product) + " ");
                }
                System.out.println();
            }
            System.out.print("Choose an order (OrderId): ");
            String inputOrder = sc.nextLine();

            Order chosenOrder = null;
            for (Order order : list) {
                if (order.getOrderId().equals(inputOrder))
                    chosenOrder = order;
            }

            System.out.print("Enter the new status (paid or processing): ");
            String newStatus = sc.nextLine();
            System.out.println();

            chosenOrder.setStatus(newStatus);

            try {
                FileWriter fw = new FileWriter("./data/Order.csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                for (Order order : list) {
                    String result = order.getOrderId() + "," + order.getMemberId() + "," + order.getOrderDate() + ","
                            + Double.toString(order.getTotal()) + ","
                            + order.getStatus() + ",";

                    for (Product product : order.getItems().keySet()) {
                        result = result.concat(product.getProductId() + ":" + order.getItems().get(product) + ",");
                    }
                    result = result.substring(0, result.length() - 1);
                    pw.println(result);
                }

                pw.flush();
                pw.close();
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }

            System.out.println("Success");
        } catch (Exception e) {
            System.out.println();
            System.out.println("Invalid Input");
        }

    }

    public static void removeProduct() {
        Scanner sc = new Scanner(System.in);
        int indexToShow = 1;
        for (String category : Product.categoryList) {
            System.out.println(indexToShow + ": " + category);
            indexToShow += 1;
        }

        try {
            System.out.print("Choose a category: ");
            int inputCategory = sc.nextInt();
            System.out.println();

            if (!(inputCategory > 0 && inputCategory <= Product.categoryList.size())) {
                System.out.println("Invalid input");
                return;
            }

            String choosenCategory = Product.categoryList.get(inputCategory - 1);
            ArrayList<Product> choosenList = Product.productMap.get(choosenCategory);
            indexToShow = 1;

            System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", "Number ", "Name", "Price", "Category", "Quantity");
            System.out.println();

            for (Product product : choosenList) {
                System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", indexToShow, product.getProductName(),
                        product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
                indexToShow += 1;
                System.out.println();
            }

            System.out.print("Choose a product to remove: ");
            int indexChosenProduct = sc.nextInt();
            System.out.println();
            choosenList.remove(indexChosenProduct - 1);

            try {
                FileWriter fw = new FileWriter("./data/" + choosenCategory + ".csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                for (Product product : choosenList) {
                    pw.println(product.getProductId() + "," + product.getProductName() + "," + product.getProductPrice()
                            + "," + product.getProductQuantity());
                }

                pw.flush();
                pw.close();
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }

            System.out.println("Success");
        } catch (Exception e) {
            System.out.println();
            System.out.println("Invalid Input");
        }

    }

}
