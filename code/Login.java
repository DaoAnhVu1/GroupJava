package code;

import java.util.Scanner;

import code.Users.Customer;

public class Login {
    public static int start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the username: ");
        String userName = sc.nextLine();
        System.out.println();
        System.out.print("Enter the password: ");
        String password = sc.nextLine();
        int indexOfUser = 0;

        if (userName.equals("admin") && password.equals("adminpassword")) {
            return -2;
        }

        for (Customer customer : Customer.allCustomer) {
            if (userName.equals(customer.getCustomerName()) && password.equals(customer.getCustomerPassword())) {
                return indexOfUser;
            }
            indexOfUser += 1;
        }

        return -1;
    }
}
