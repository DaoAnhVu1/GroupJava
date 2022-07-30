package Products;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private final String productId;
    private final String productName;
    private double productPrice;
    private int productQuantity;
    private final String productCategory;
    public static ArrayList<String> categoryList = new ArrayList<>();
    public static ArrayList<Product> allProduct = new ArrayList<>();
    public static HashMap<String, ArrayList<Product>> productMap = new HashMap<>();

    public Product(String productId, String productName, double productPrice,String productCategory ,int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
        Product.allProduct.add(this);
        if (productMap.containsKey(productCategory)) {
            productMap.get(productCategory).add(this);
        } else {
            ArrayList<Product> newList = new ArrayList<>();
            categoryList.add(productCategory);
            newList.add(this);
            productMap.put(productCategory, newList);
        }
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductCategory() {
        return productCategory;
    }
}