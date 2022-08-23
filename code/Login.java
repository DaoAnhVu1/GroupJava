import java.util.Scanner;

import Users.Member;

//Function 2: Login with the registered username and password
public class Login {
    public static int start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the username: ");
        String userName = sc.nextLine();
        System.out.println();
        System.out.print("Enter the password: ");
        String password = sc.nextLine();
        System.out.println();
        int indexOfUser = 0;

        //Function 8: Admin can login with a predefined username and password
        if (userName.equals("admin") && password.equals("adminpassword")) {
            return -2;
        }

        for (Member member : Member.allMember) {
            if (userName.equals(member.getMemberName()) && password.equals(member.getMemberPassword())) {
                return indexOfUser;
            }
            indexOfUser += 1;
        }

        return -1;
    }
}
