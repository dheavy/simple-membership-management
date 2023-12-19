package com.davybraun.membershipmanagement;

import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        String mem;

        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = fh.readFile();

        int choice = mm.getChoice();

        while (choice != -1) {
            switch (choice) {
                case 1 -> {
                    mem = mm.addMembers(members);
                    fh.appendFile(mem);
                }
                case 2 -> {
                    mm.removeMember(members);
                    fh.overwriteFile(members);
                }
                case 3 -> mm.printMemberInfo(members);
                default -> System.out.println("Invalid input. Please try again.");
            }
            choice = mm.getChoice();
        }

        System.out.println("\nThank you for using Ozone Fitness Center Membership Management System");
    }
}
