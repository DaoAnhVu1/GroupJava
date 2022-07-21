import java.util.ArrayList;

import Products.Product;
import Users.Guess;

public class test {
    public static void main(String[] args) {
        Main.loadProductData("./data/Console.csv");
        ArrayList<Product> allItemsList = new ArrayList<>();
        for (String category : Product.productMap.keySet()) {
            for (Product product : Product.productMap.get(category)) {
               allItemsList.add(product);
            }
        }

        Guess.quicksortPrice(allItemsList, 0, allItemsList.size()-1);

        for (Product product : allItemsList) {
            System.out.println(product.getProductPrice());
        }
    }
}
