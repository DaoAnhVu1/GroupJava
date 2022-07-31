package Users;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import Order.Order;
import Products.Product;
import Wishlist.Wishlist;

public class Member extends Guest {
    public static ArrayList<Member> allMember = new ArrayList<>();
    private final String memberId;
    private final String memberName;
    private String memberPassword;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
    private double memberMoneySpent;
    private String memberLevel;

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

    public void newPassword(String newPass) {
        this.memberPassword = newPass;
    }

    public void memberStart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello " + this.getMemberName());
        while (true) {
            try {
                System.out.println();
                System.out.println("Choose what you want to do: ");
                System.out.println("1: Your Info");
                System.out.println("2: Show all items by details");
                System.out.println("3: Show items by category");
                System.out.println("4: Show items by price");
                System.out.println("5: Create an order");
                System.out.println("6: View an order");
                System.out.println("7: Create your wishlist");
                System.out.println("8: View your wishlist");
                System.out.println("9: Edit your profile");
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
                    viewOrder();
                } else if (input == 7) {
                    createWishlist();
                } else if (input == 8) {
                    viewWishList();
                } else if (input == 9) {
                    editProfile();
                } else {
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
        System.out.println("Status: " + this.getMemberLevel());
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

                String chosenCategory = Product.categoryList.get(inputCategory - 1);
                allChosenCategory.add(chosenCategory);
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
                if (!(indexChosenProduct > 0 && indexChosenProduct <= chosenList.size())) {
                    System.out.println("Invalid input");
                    return;
                }
                Product chosenProduct = chosenList.get(indexChosenProduct - 1);

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

    public void viewOrder() {
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<Order> list = Order.allOrder;
            System.out.print("Enter an order ID: ");
            String input = sc.nextLine();
            System.out.println();

            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s", "OrderId ", "MemberId", "Date", "Total", "Status",
                    "Product");
            System.out.println();

            for (Order order : list) {
                if (order.getOrderId().equals(input)) {

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

                } else {
                    System.out.println();
                    System.out.println("There is no order from this ID");
                    return;
                }

            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("Invalid order ID!");
        }
    }

    public void createAndWriteVoucher(){

    }

    public void createWishlist() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> wishList = new ArrayList<>();
        ArrayList<Integer> inputList = new ArrayList<>();
        Guest.showAllItemsDetails();

        System.out.println();

        while (true) {
            System.out.println("If you want to stop, press 0");
            System.out.println("Add a product to your wishlist: ");
            int inputWishList = sc.nextInt();

            if (inputWishList == 0) {
                break;
            }

            if (!(inputWishList > 0 && inputWishList <= Product.allProduct.size())) {
                System.out.println("Invalid input");
                return;
            }

            if (!(inputList.contains(inputWishList))) {
                inputList.add(inputWishList);
                Product inputProduct = Product.allProduct.get(inputWishList - 1);
                wishList.add(inputProduct);
            } else {
                System.out.println("This product is already in your wishlist");
                System.out.println();
            }

        }

        if (wishList.isEmpty()) {
            System.out.println("Your wishlist is empty");
        } else {
            Wishlist newWishList = new Wishlist(this.memberId, wishList);
            writeWishlist(this.getMemberId(), wishList);
        }

    }

    public void writeWishlist(String memberID, ArrayList<Product> wishList) {

        try {
            // Write to wishlist
            FileWriter fw = new FileWriter("./data/wishlist.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String result = memberId + ",";

            for (Product product : wishList) {
                result = result.concat(product.getProductId() + ",");

            }
            pw.println(result);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public void viewWishList() {
        String filePath = "./data/wishlist.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                if (line.contains("ï»¿")) {
                    line = line.replace("ï»¿", "");
                }
                String[] row = line.split(",");

                System.out.printf("|%-10s|%-10s", "Your ID", "Product");
                System.out.println();

                for (String index : row) {
                    if (row[0].equals(memberId)) {
                        System.out.printf("|%-10s", index);
                    }
                }
                System.out.println();
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
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

    public void editProfile() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Chose what you want to change");
            System.out.println("1: Your password");
            System.out.println("2: Your phone");
            System.out.println("3: Your email");
            System.out.println("4: Your address");
            System.out.printf("Enter: ");

            while (true) {

                int inputChange = sc.nextInt();

                if (inputChange == 1) {
                    System.out.printf("Enter your new password: ");
                    String newPass = sc.nextLine();
                    if (newPass != null) {
                        this.setMemberPassword(newPass);

                    } else {
                        System.out.println("Invalid input");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

    }
}