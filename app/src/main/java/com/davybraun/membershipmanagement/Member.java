package com.davybraun.membershipmanagement;

public class Member {

    private char memberType;
    private int memberID;
    private String name;
    private Double fees;

    public Member(char memberType, int memberID, String name, Double fees) {
        this.memberType = memberType;
        this.memberID = memberID;
        this.name = name;
        this.fees = fees;
    }

    public char getMemberType() {
        return memberType;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public Double getFees() {
        return fees;
    }

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return memberType + " " + memberID + " " + name + " " + fees;
    }

}
