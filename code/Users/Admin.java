package Users;

import java.util.ArrayList;
import java.util.Scanner;

import Products.Product;

public class Admin extends Guess {
    public static void adminStart() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Choose your actions: ");
            System.out.println("1: Show all users");
            System.out.println("2: Show all items and its details");
            System.out.println("3: Show all items by category");
            System.out.println("4: Show all items by price");
            System.out.println("5: Change product's price");
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
                Guess.showAllItemsDetails();
            } else if (input == 3) {
                Guess.showAllItemsCategory();
            } else if (input == 4) {
                Guess.showAllItemsByPrice();
            } else if (input == 5) {
                Admin.changeProductPrice();
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
        System.out.print("Choose a category: ");
        int inputCategory = sc.nextInt();
        System.out.println();
        String choosenCategory = Product.categoryList.get(inputCategory - 1);
        ArrayList<Product> choosenList = Product.productMap.get(choosenCategory);
        indexToShow = 1;

        System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", "Number ", "Name", "Price", "Category", "Quantity");
        System.out.println();

        for (Product product : choosenList) {
            System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", indexToShow, product.getProductName(),
                    product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
            indexToShow +=1;
            System.out.println();
        }

        System.out.print("Choose a product: ");
        int indexChosenProduct = sc.nextInt();
        System.out.println();

        Product chosenProduct = choosenList.get(indexChosenProduct-1);
        System.out.print("Enter the new price: ");
        double newPrice = sc.nextDouble();
        System.out.println();

        chosenProduct.setProductPrice(newPrice);
        System.out.println("Success");
    }

}
