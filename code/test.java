import Products.Product;
import Wishlist.Wishlist;

public class test {
    public static void main(String[] args) {
        Main.loadProductData("./data/console.csv");
        Main.loadWishListData();
        for (Wishlist wishlist : Wishlist.allWishList) {
            for (Product product : wishlist.getWishList()) {
                System.out.println(product.getProductName());
            }
        }
    }
}