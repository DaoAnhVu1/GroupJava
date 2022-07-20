package code.Users;
import java.util.Scanner;

public class Admin {

    public static void showAllUser () {
        for (Customer customer : Customer.allCustomer) {
            System.out.println(customer.getCustomerId());
            System.out.println(customer.getCustomerName());
            System.out.println(customer.getCustomerPassword());
            System.out.println();
        }
    }

    public static void adminStart() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose your actions: ");
            System.out.println("1: Show all users");
            System.out.println("2: Show more things");
            System.out.println("0: Logout");
            int input = sc.nextInt();
            if (input == 0) {
                break;
            } else if (input == 1) {
                showAllUser();
            } else if (input == 2) {
                System.out.println("Developing");
            } else {
                System.out.println("There is an error");
            }
        }
    }
}
