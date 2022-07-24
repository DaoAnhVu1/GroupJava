import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import Users.Member;

public class Register {
    public static void start() {
        String id = UUID.randomUUID().toString().substring(0,8);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String userName = sc.nextLine();
        System.out.println();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        System.out.println();
        System.out.print("Enter your phone: ");
        String phone = sc.nextLine();
        System.out.println();
        System.out.print("Enter your email: ");
        String email  = sc.nextLine();
        System.out.println();
        System.out.print("Enter your address: ");
        String address = sc.nextLine();
        System.out.println();

        try {
            FileWriter fw = new FileWriter("./data/Member.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(id+","+userName+","+password+","+phone+","+email+","+address+","+0);
            pw.flush();
            pw.close();
            System.out.println("Success");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        Member member = new Member(id, userName, password, phone, email, address, 0);
    }
}
