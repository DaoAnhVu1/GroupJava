package Wishlist;

import Products.Product;
import Users.Member;

import java.util.ArrayList;
import java.util.HashMap;

public class Wishlist {
    private String memberId;
    private ArrayList<Product> wishList = new ArrayList<>();

    public Wishlist(String memberId, ArrayList<Product> wishList) {
        this.memberId = memberId;
        this.wishList = wishList;
    }

    public String getMemberId() {
        return memberId;
    }

    public ArrayList<Product> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Product> wishList) {
        this.wishList = wishList;
    }
}
