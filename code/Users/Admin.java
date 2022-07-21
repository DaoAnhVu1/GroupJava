package Users;


import java.util.Scanner;

public class Admin {

    public static void showAllUser () {
        for (Member member : Member.allMember) {
            System.out.println(member.getMemberId());
            System.out.println(member.getMemberName());
            System.out.println(member.getMemberPassword());
            System.out.println();
        }
    }

    public static void adminStart() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Choose your actions: ");
            System.out.println("1: Show all users");
            System.out.println("2: Show more things");
            System.out.println("0: Logout");
            System.out.println();
            System.out.printf("Your input: ");
            int input = sc.nextInt();
            System.out.println();
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
