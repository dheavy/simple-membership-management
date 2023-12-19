package com.davybraun.membershipmanagement;

import java.util.LinkedList;
import java.io.*;

public class FileHandler {

    public LinkedList<Member> readFile() {
        LinkedList<Member> members = new LinkedList<Member>();
        String lineRead;
        String[] splitLine;
        Member member;

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            lineRead = reader.readLine();

            while (lineRead != null) {
                splitLine = lineRead.split(", ");
                if (splitLine[0].equals("S")) {
                    member = new SingleClubMember('S', Integer.parseInt(splitLine[1]), splitLine[2],
                            Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                    members.add(member);
                } else {
                    member = new MultiClubMember('M', Integer.parseInt(splitLine[1]), splitLine[2],
                            Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                    members.add(member);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return members;
    }

    public void appendFile(String member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))) {
            writer.write(member);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void overwriteFile(LinkedList<Member> members) {
        String s;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.tmp", false))) {
            for (Member member : members) {
                s = member.toString();
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        try {
            File file = new File("members.csv");
            File file2 = new File("members.tmp");
            file.delete();
            file2.renameTo(file);
        } catch (Exception e) {
            System.out.println("Error renaming file: " + e.getMessage());
        }
    }
}
