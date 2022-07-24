

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import Order.Order;
import Products.Product;
import Users.Member;

public class test {
    public static void main(String[] args) {
        Main.loadOrderData();
        Main.loadMemberData();
        for (Member member : Member.allMember) {
            System.out.println(member.getMemberLevel());
        }
    }
}