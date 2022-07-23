package Users;

import java.util.ArrayList;
import java.util.Scanner;

import Products.Product;

public abstract class Guest {
    public static void showAllItemsDetails() {
        System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5s", "Number ", "Name", "Price", "Category", "Quantity");
        int index = 1;
        System.out.println();
        for (String category : Product.productMap.keySet()) {
            for (Product product : Product.productMap.get(category)) {
                System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", index, product.getProductName(),
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

        System.out.println();
        System.out.print("Your input: ");
        int input = sc.nextInt();
        System.out.println();

        String userSelection = Product.categoryList.get(input - 1);

        System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5ss", "Number ", "Name", "Price", "Category", "Quantity");
        System.out.println();

        for (Product product : Product.productMap.get(userSelection)) {
            System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", productIndex, product.getProductName(),
                    product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
            productIndex += 1;
            System.out.println();
        }
    }

    public static void showAllItemsByPrice() {
        ArrayList<Product> allItemsList = new ArrayList<>();
        for (String category : Product.productMap.keySet()) {
            for (Product product : Product.productMap.get(category)) {
               allItemsList.add(product);
            }
        }

        quicksortPrice(allItemsList, 0, allItemsList.size() - 1);

        System.out.printf("|%-10s|%-25s|%-15s|%-15s|%-5s", "Number ", "Name", "Price", "Category", "Quantity");
        System.out.println();
        int index = 1;
        for (Product product : allItemsList) {
            System.out.printf("|%-10s|%-25s|%-15.0f|%-15s|%-5s", index, product.getProductName(),
            product.getProductPrice(), product.getProductCategory(), product.getProductQuantity());
            index += 1;
            System.out.println();
        }

    }

    public static void quicksortPrice(ArrayList<Product> list, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }

        double pivot = list.get(highIndex).getProductPrice();
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {
            while (list.get(leftPointer).getProductPrice() <= pivot && leftPointer < rightPointer) {
                leftPointer +=1;
            }

            while (list.get(rightPointer).getProductPrice() >= pivot && leftPointer < rightPointer) {
                rightPointer -=1;
            }

            swap(list, leftPointer, rightPointer);
        }

        swap(list, leftPointer, highIndex);
        quicksortPrice(list, lowIndex, leftPointer-1);
        quicksortPrice(list, leftPointer+1, highIndex);
    }
    
    public static void swap(ArrayList<Product> list, int firstIndex, int secondIndex) {
        Product temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }
}
