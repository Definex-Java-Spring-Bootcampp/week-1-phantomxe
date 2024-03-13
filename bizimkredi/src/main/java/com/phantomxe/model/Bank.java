package com.phantomxe.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    private List<Loan> loanList;
    private List<CreditCard> creditCards;

    public Bank(String name) {
        this.name = name;
        loanList = new ArrayList<>();
        creditCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public void addCreditCard(CreditCard cc) {
        creditCards.add(cc);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", loanList=" + loanList +
                '}';
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }
}
