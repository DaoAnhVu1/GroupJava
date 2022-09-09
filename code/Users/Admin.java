package Users;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import Products.Product;
import Order.Order;


public class Admin extends Guest {
    public static void adminStart() {
        Scanner sc = new Scanner(System.in);

        //Admin selection menu
        while (true) {
            System.out.println();
            System.out.println("Welcome ADMIN");
            System.out.println("Choose your actions: ");
            System.out.println("1: Show all members");
            System.out.println("2: Show all items and its details");
            System.out.println("3: Show all items by category");
            System.out.println("4: Show all items by price");
            System.out.println("5: Change product's price");
            System.out.println("6: Add new product");
            System.out.println("7: Remove product");
            System.out.println("8: View order");
            System.out.println("9: Change order status");
            System.out.println("10: View revenue");
            System.out.println("0: Logout");
            System.out.println();
            System.out.printf("Your input: ");
            int input = sc.nextInt();
            System.out.println();
            if (input == 0) {
                break;
            } else if (input == 1) {
                showAllMember();
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
                viewAllOrder();
            } else if (input == 9) {
                Admin.changeOrderStatus();
            } else if (input == 10) {
                Admin.viewRevenue();
            }
        }
    }

    //Function 8: Admin view information of all members
    public static void showAllMember() {
        for (Member member : Member.allMember) {
            System.out.println("ID: " + member.getMemberId());
            System.out.println("Name: " + member.getMemberName());
            System.out.println("Email: " + member.getMemberEmail());
            System.out.println("Address: " + member.getMemberAddress());
            System.out.printf("Spent: %.0f",member.getMemberMoneySpent());
            System.out.println();
            System.out.println("Level: " + member.getMemberLevel());
            System.out.println();
        }
    }

    //Function 10: Admin can update price for a particular product
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

            String chosenCategory = Product.categoryList.get(inputCategory - 1);
            ArrayList<Product> chosenList = Product.productMap.get(chosenCategory);
            indexToShow = 1;

            System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", "Number ", "Name", "Price", "Category", "Quantity");
            System.out.println();

            for (Product product : chosenList) {
                System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", indexToShow, product.getProductName(),
                        product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
                indexToShow += 1;
                System.out.println();
            }

            System.out.print("Choose a product: ");
            int indexChosenProduct = sc.nextInt();
            System.out.println();

            Product chosenProduct = chosenList.get(indexChosenProduct - 1);
            System.out.print("Enter the new price: ");
            double newPrice = sc.nextDouble();
            System.out.println();

            chosenProduct.setProductPrice(newPrice);

            try {
                FileWriter fw = new FileWriter("./data/" + chosenCategory + ".csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                for (Product product : chosenList) {
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

    //Function 9: Admin can add a new product to the store
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
            String chosenCategory = Product.categoryList.get(inputCategory - 1);

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
                FileWriter fw = new FileWriter("./data/" + chosenCategory + ".csv", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(id + "," + productName + "," + productPrice + "," + productQuantity);
                pw.flush();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Product newProduct = new Product(id, productName, Double.parseDouble(productPrice), chosenCategory,
                    Integer.parseInt(productQuantity));
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    //Additional feature: remove the product
    public static void removeProduct() {
        try {
            Scanner sc = new Scanner(System.in);
            int indexToShow = 1;
            int indexProduct = 1;
            for (String category : Product.categoryList) {
                System.out.println(indexToShow + ": " + category);
                indexToShow += 1;
            }
            System.out.print("Choose a category: ");
            int inputCategory = sc.nextInt();

            System.out.println();
            String chosenCategory = Product.categoryList.get(inputCategory - 1);

            ArrayList<Product> chosenList = Product.productMap.get(chosenCategory);

            System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", "Number ", "Name", "Price", "Category", "Quantity");
            System.out.println();

            for (Product product : chosenList) {
                System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", indexProduct, product.getProductName(),
                        product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
                indexProduct += 1;
                System.out.println();
            }

            System.out.print("Choose a product: ");
            int indexChosenProduct = sc.nextInt();

            Product chosenProduct = chosenList.get(indexChosenProduct - 1);

            ArrayList<Product> toRemove1 = new ArrayList<>();
            ArrayList<Product> toRemove2 = new ArrayList<>();

            for (Product product : chosenList) {
                if (product.getProductId().equals(chosenProduct.getProductId())) {
                    toRemove1.add(product);
                }
            }

            for (Product product1 : Product.productMap.get(chosenCategory)) {
                if (product1.getProductId().equals(chosenProduct.getProductId())) {
                    toRemove2.add(product1);
                }
            }

            Product.allProduct.removeAll(toRemove1);
            Product.productMap.get(chosenCategory).removeAll(toRemove2);

            try {
                FileWriter fw = new FileWriter("./data/" + chosenCategory + ".csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                for (Product product : chosenList) {
                    pw.println(product.getProductId() + "," + product.getProductName() + "," + product.getProductPrice()
                            + "," + product.getProductQuantity());
                }

                pw.flush();
                pw.close();
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }

            System.out.println();
            System.out.println("Success");

        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    //Function 11: Admin view information of all orders by customer ID
    public static void viewAllOrder() {
        for (Member member : Member.allMember) {
            System.out.println("ID: " + member.getMemberId());
            System.out.println("Name: " + member.getMemberName());
            System.out.println();
        }
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<Order> list = Order.allOrder;
            System.out.printf("Enter the member ID of the order: ");
            String inputId = sc.nextLine();
            boolean found = false;

            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s", "OrderId ", "MemberId", "Date", "Total", "Status",
                    "Product");
            System.out.println();

            for (Order order : list) {
                if (order.getMemberId().equals(inputId)) {

                    found = true;

                    String id = order.getOrderId();
                    String memberId = order.getMemberId();
                    String date = order.getOrderDate();
                    String total = Double.toString(order.getTotal());
                    String status = order.getStatus();

                    System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-15s", id, memberId, date, total, status);
                    for (Product product : order.getItems().keySet()) {
                        System.out.printf("|%-20s", product.getProductName());
                        System.out.print(" : " + order.getItems().get(product) + " ");
                    }
                    System.out.println();
                }

            }

            if (!found) {
                System.out.println();
                System.out.println("There is no order with this ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
             System.out.println();
             System.out.println("Invalid member ID!");
        }
    }

    //Function 12: Admin can change the status of the order
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
            boolean found = false;

            Order chosenOrder = null;
            for (Order order : list) {
                if (order.getOrderId().equals(inputOrder)) {
                    chosenOrder = order;
                    found = true;
                }
            }

            if (!found) {
                System.out.println();
                System.out.println("Wrong ID");
                return;
            } else {
                System.out.print("Enter the new status (paid or processing): ");
                String newStatus = sc.nextLine();
                if (newStatus.equals("paid") || newStatus.equals("processing")) {
                    System.out.println();
                    chosenOrder.setStatus(newStatus);
                } else {
                    System.out.println();
                    System.out.println("Wrong status");
                    return;
                }

            }

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

    //Calculate the store total revenue  at the end of the day
    public static void viewRevenue() {
        try {
            double total = 0;
            ArrayList<Order> list = Order.allOrder;

            Date thisDate = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/Y");
            String dateString = dateForm.format(thisDate);
            for (Order order : list) {
                if (dateString.equals(order.getOrderDate()) && order.getStatus().equals("paid")) {
                    total += order.getTotal();
                }
            }
            if (total != 0) {
                System.out.println("Revenue of today: " + total);
            } else {
                System.out.println("There is no order from today");
                System.out.println("Revenue: 0");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
}
