package code.Users;

import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String customerName;
    private String customerPassword;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    private double customerMoneySpent;
    public static ArrayList<Customer> allCustomer = new ArrayList<Customer>();

    public Customer(String customerId, String customerName, String customerPassword, String customerPhone, String customerEmail, String customerAddress, double customerMoneySpent) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPassword = customerPassword;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerMoneySpent = customerMoneySpent;
        allCustomer.add(this);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getCustomerMoneySpent() {
        return customerMoneySpent;
    }

    public void setCustomerMoneySpent(double customerMoneySpent) {
        this.customerMoneySpent = customerMoneySpent;
    }

    public void customerStart() {
        System.out.println("Hello " + this.getCustomerName());
        System.out.println("Choose what you want to do: ");
    }
}
