package com.davybraun.membershipmanagement;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getInput() {
        int choice = 0;

        while (choice == 0) {
            try {
                choice = Integer.parseInt(reader.nextLine());

                if (choice == 0)
                    throw new InputMismatchException();

                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("Invalid input. Please try again.");
            }
        }
        return choice;
    }

    private void printClubOptions() {
        System.out.println("\n1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    public int getChoice() {
        int choice = 0;

        System.out.println("\nWelcome to Ozone Fitness Center");
        System.out.println("================================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println("\nPlease select an option (or Enter -1 to quit): ");

        choice = getInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> m) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> calc;

        System.out.println("\nPlease enter member's name: ");
        name = reader.nextLine();

        printClubOptions();
        System.out.println("\nPlease enter member's club ID: ");
        club = getInput();

        while (club < 1 || club > 4) {
            System.out.println("Invalid input. Please try again.");
            club = getInput();
        }

        if (m.size() > 0) {
            memberID = m.getLast().getMemberID() + 1;
        } else {
            memberID = 1;
        }

        if (club != 4) {
            calc = (n) -> {
                return switch (n) {
                    case 1 -> 900;
                    case 2 -> 950;
                    case 3 -> 1000;
                    default -> 0;
                };
            };

            fees = calc.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSingle Club Member added successfully\n");
        } else {
            calc = (n) -> {
                if (n == 4) {
                    return 1200;
                } else {
                    return 0;
                }
            };

            fees = calc.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nMulti Club Member added successfully\n");
        }

        return mem;
    }

    public void printMemberInfo(LinkedList<Member> m) {
        int memberID;
        boolean found = false;

        System.out.println("\nPlease enter member ID: ");
        memberID = getInput();

        for (Member member : m) {
            if (member.getMemberID() == memberID) {
                found = true;
                System.out.println(member.toString());
                break;
            }
        }

        if (!found) {
            System.out.println("\nMember " + memberID + " not found\n");
        }
    }

    public void removeMember(LinkedList<Member> m) {
        int memberID;
        int index = -1;
        boolean found = false;

        System.out.println("\nPlease enter member ID to remove: ");
        memberID = getInput();

        for (Member member : m) {
            if (member.getMemberID() == memberID) {
                found = true;
                index = m.indexOf(member);
                break;
            }
        }

        if (found) {
            m.remove(index);
            System.out.println("\nMember " + memberID + " removed successfully\n");
        } else {
            System.out.println("\nMember " + memberID + " not found\n");
        }
    }
}
