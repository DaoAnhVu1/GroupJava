package Order;

import Products.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private String orderId;
    private String memberId;
    private String orderDate;
    private double total;
    private String status;
    private HashMap<Product, Integer> items = new HashMap<>();
    public static ArrayList<Order> allOrder = new ArrayList<>();

    public Order(String orderId, String memberId, String orderDate, double total, String status, HashMap<Product, Integer> items) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
        this.items = items;
        Order.allOrder.add(this);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
