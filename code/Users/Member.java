package Users;

import java.util.ArrayList;
import java.util.Scanner;

public class Member extends Guess{
    private String memberId;
    private String memberName;
    private String memberPassword;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
    private double memberMoneySpent;
    public static ArrayList<Member> allMember = new ArrayList<Member>();

    public Member(String memberId, String memberName, String memberPassword, String memberPhone,
            String memberEmail, String memberAddress, double memberMoneySpent) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
        this.memberMoneySpent = memberMoneySpent;
        allMember.add(this);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public double getMemberMoneySpent() {
        return memberMoneySpent;
    }

    public void setMemberMoneySpent(double memberMoneySpent) {
        this.memberMoneySpent = memberMoneySpent;
    }

    public void memberStart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello " + this.getMemberName());
        while (true) {
            System.out.println();
            System.out.println("Choose what you want to do: ");
            System.out.println("1: Your Info");
            System.out.println("2: Show all items by details: ");
            System.out.println("0: Logout");
            System.out.println();
            System.out.print("Your input: ");
            int input = sc.nextInt();
            System.out.println();
            if (input == 0) {
                break;
            } else if (input == 1) {
                this.showAllInfo();
            } else if (input == 2) {
                Guess.showAllItemsDetails();
            }
        }
    }

    public void showAllInfo() {
        System.out.println("Name: " + this.getMemberName());
        System.out.println("Phone: " + this.getMemberPhone());
        System.out.println("Email: " + this.getMemberEmail());
        System.out.println("Address: " + this.getMemberAddress());
    }
}
