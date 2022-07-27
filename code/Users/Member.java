package Users;

import java.sql.SQLOutput;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import Order.Order;
import Products.Product;

public class Member extends Guest {
    private final String memberId;
    private String memberName;
    private String memberPassword;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
    private double memberMoneySpent;
    private String memberLevel;
    public static ArrayList<Member> allMember = new ArrayList<>();

    public Member(String memberId, String memberName, String memberPassword, String memberPhone,
            String memberEmail, String memberAddress, double memberMoneySpent) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
        this.memberMoneySpent = memberMoneySpent;
        if (this.memberMoneySpent >= 5000000 && this.memberMoneySpent < 10000000) {
            this.memberLevel = "Silver";
        } else if (this.memberMoneySpent >= 10000000 && this.memberMoneySpent < 15000000) {
            this.memberLevel = "Gold";
        } else if (this.memberMoneySpent >= 15000000) {
            this.memberLevel = "Platinum";
        } else {
            this.memberLevel = "normal";
        }
        allMember.add(this);
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public double getMemberMoneySpent() {
        return memberMoneySpent;
    }

    public void setMemberMoneySpent(double memberMoneySpent) {
        this.memberMoneySpent = memberMoneySpent;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public void memberStart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello " + this.getMemberName());
        while (true) {
            try {
                System.out.println();
                System.out.println("Choose what you want to do: ");
                System.out.println("1: Your Info");
                System.out.println("2: Show all items by details: ");
                System.out.println("3: Show items by category");
                System.out.println("4: Show items by price");
                System.out.println("5: Create an order");
                System.out.println("6: Get information of an order");
                System.out.println("0: Logout");
                System.out.println();
                System.out.print("Your input: ");
                int input = sc.nextInt();
                System.out.println();
                if (input == 0) {
                    break;
                } else if (input == 1) {
                    this.showAllInfo();
                } else if (input == 2) {
                    Guest.showAllItemsDetails();
                } else if (input == 3) {
                    Guest.showAllItemsCategory();
                } else if (input == 4) {
                    Guest.showAllItemsByPrice();
                } else if (input == 5) {
                    orderProcess();
                } else if (input == 6) {
                    this.viewOrder();
                }
                    else {
                    System.out.println("Invalid Input");
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("Something went wrong");
            }
        }
    }

    public void showAllInfo() {
        System.out.println("Name: " + this.getMemberName());
        System.out.println("Phone: " + this.getMemberPhone());
        System.out.println("Email: " + this.getMemberEmail());
        System.out.println("Address: " + this.getMemberAddress());
    }

    public void orderProcess() {
        HashMap<Product, Integer> cart = new HashMap<>();
        ArrayList<String> allChosenCategory = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                int indexToShow = 1;
                for (String category : Product.categoryList) {
                    System.out.println(indexToShow + ": " + category);
                    indexToShow += 1;
                }
                System.out.println();
                System.out.println("0: Stop");
                System.out.println();
                System.out.print("Choose a category: ");
                int inputCategory = sc.nextInt();
                System.out.println();

                if (inputCategory == 0) {
                    break;
                }

                if (!(inputCategory > 0 && inputCategory <= Product.categoryList.size())) {
                    System.out.println("Invalid input");
                    return;
                }

                String choosenCategory = Product.categoryList.get(inputCategory - 1);
                allChosenCategory.add(choosenCategory);
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
                if (!(indexChosenProduct > 0 && indexChosenProduct <= indexChosenProduct)) {
                    System.out.println("Invalid input");
                    return;
                }
                Product chosenProduct = choosenList.get(indexChosenProduct - 1);

                System.out.print("How many do you want to buy ? ");
                int quantity = sc.nextInt();
                System.out.println();

                int currentQuantity = chosenProduct.getProductQuantity();

                chosenProduct.setProductQuantity(currentQuantity - quantity);
                cart.put(chosenProduct, quantity);

                System.out.print("If you wish to continue, press 1, else press any other number: ");
                int proceed = sc.nextInt();
                System.out.println();
                if (!(proceed == 1)) {
                    break;
                }
            }

            if (!(cart.isEmpty())) {
                createAndWriteOrder(this.getMemberId(), this.getMemberLevel(), cart, allChosenCategory);
            } else {
                System.out.println("Your cart is empty");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public void createAndWriteOrder(String memberId, String memberLevel, HashMap<Product, Integer> cart, ArrayList<String> allChosenCategory) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        Date thisDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/Y");
        String dateString = dateForm.format(thisDate);
        double total = 0;
        String status = "processing";

        for (Product product : cart.keySet()) {
            total += product.getProductPrice() * cart.get(product);
        }

        if (memberLevel.equals("Silver")) {
            total = total * 0.95;
        } else if (memberLevel.equals("Gold")) {
            total = total * 0.9;
        } else if (memberLevel.equals("Platinum")) {
            total = total * 0.85;
        }

        public void viewOrder() {
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter your order ID: ");
            int input = sc.nextInt();
            System.out.println();

            String userChoice = Order.allOrder.get(input - 1);
            System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", "OrderID", "MemberID", "Total", "Status", "Items");
            System.out.println();

            System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", orderId, memberId, total, status);
        }

        Order order = new Order(orderId, memberId, dateString, total, status, cart);


        try {
            // Write to order
            FileWriter fw = new FileWriter("./data/Order.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String result = orderId + "," + memberId + "," + dateString + "," + Double.toString(total) + "," + status
                    + ",";

            for (Product product : cart.keySet()) {
                result = result.concat(product.getProductId() + ":" + cart.get(product) + ",");
            }

            result = result.substring(0, result.length() - 1);
            pw.println(result);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        try {
            // Write to Product file;
            for (String chosenCategory : allChosenCategory) {
                FileWriter fw = new FileWriter("./data/" + chosenCategory + ".csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                for (Product product : Product.productMap.get(chosenCategory)) {
                    System.out.println(product.getProductName());
                    pw.println(product.getProductId()+","+product.getProductName()+","+product.getProductPrice()+","+product.getProductQuantity());
                }
                pw.flush();
                pw.close();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        try {
            // Write to member
            FileWriter fw = new FileWriter("./data/member.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            double currentMoneySpent = this.getMemberMoneySpent();
            this.setMemberMoneySpent(currentMoneySpent + total);

            if (this.getMemberMoneySpent() >= 5000000 && this.getMemberMoneySpent() < 10000000) {
                this.setMemberLevel("Silver");
            } else if (this.getMemberMoneySpent() >= 10000000 && this.getMemberMoneySpent() < 15000000) {
                this.setMemberLevel("Gold");
            } else if (this.getMemberMoneySpent() >= 15000000) {
                this.setMemberLevel("Platinum");
            }

            System.out.println(this.getMemberLevel());

            for (Member member : allMember) {
                pw.println(
                        member.getMemberId() + "," + member.getMemberName() + "," + member.getMemberPassword() + ","
                                + member.getMemberPhone() + "," + member.getMemberEmail() + ","
                                + member.getMemberAddress() + "," + member.getMemberMoneySpent());
            }

            pw.flush();
            pw.close();
            System.out.println("Sucess");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
}
