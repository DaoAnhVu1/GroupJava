package Users;

import java.util.Scanner;

import Products.Product;

public class Guess {
    public static void showAllItemsDetails() {
        System.out.printf("%-10s%-25s%-20s%-15s%-5s", "Number ", "Name", "Price", "Category", "Quantity");
        int index = 1;
        System.out.println();
        for (String category : Product.productMap.keySet()) {
            for (Product product : Product.productMap.get(category)) {
                System.out.printf("%-10s%-25s%-20s%-15s%-5s", index, product.getProductName(),
                        product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
                index += 1;
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void showAllItemsCategory() {
        int categoryIndex = 1;
        int productIndex = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the category you want to view: ");
        for (String category : Product.categoryList) {
            System.out.println(categoryIndex + ": " + category);
            categoryIndex += 1;
        }

        System.out.print("Your input: ");
        int input = sc.nextInt();
        String userSelection = Product.categoryList.get(input - 1);

        for (Product product : Product.productMap.get(userSelection)) {
            System.out.printf("%-10s%-25s%-20s%-15s%-5s", productIndex, product.getProductName(),
                    product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
            productIndex += 1;
            System.out.println();
        }
    }
}
