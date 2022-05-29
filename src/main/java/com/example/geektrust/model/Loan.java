package com.example.geektrust.model;

import java.util.HashMap;
import java.util.Map;

public class Loan {
    private  Bank bank;
    private  Customer customer;
    private  double principle;
    private  double interest;
    private  double term;
    private  Map<Integer, Integer> payments;
    private  double amount;
    private Integer noOfEmis;
    private Integer emiAmount;

    public Loan(Bank bank, Customer customer, double principle, double interest, double term) {
        this.payments = new HashMap<>();
        this.bank = bank;
        this.customer = customer;
        this.principle = principle;
        this.interest = interest;
        this.term = term;
        this.amount = computeAmount(principle, interest, term);
        this.noOfEmis = computeNoOfEMIs(term);
        this.emiAmount = computeEmiAmount(amount, noOfEmis);
    }

    public Bank getBank() {
        return bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getPrinciple() {
        return principle;
    }

    public double getInterest() {
        return interest;
    }

    public double getTerm() {
        return term;
    }

    public Map<Integer, Integer> getPayments() {
        return payments;
    }

    public double getAmount() {
        return amount;
    }

    public Integer getAmountPaid(Integer installment){
        Integer lumpSum =  payments.getOrDefault(installment, 0);
        Integer totalEmis = installment * emiAmount;
        Integer totalAmountToBePaid = Math.toIntExact(Math.round(amount));
        return Math.min(lumpSum + totalEmis, totalAmountToBePaid);
    }

    public Integer getRemainingEmis(Integer amountPaid){
        return (int) Math.ceil((amount - amountPaid)/ emiAmount);
    }

    public void addPayment(Integer installment, Integer amount){
        payments.put(installment, amount);
    }

    private double computeAmount(double principle, double interest, double term){
        return principle + (principle * interest * term)/100;
    }

    private Integer computeNoOfEMIs(double term){
        return  (int)Math.ceil(term * 12);
    }

    private Integer computeEmiAmount(Double amount, Integer term){
        return (int) Math.ceil(amount / term * 12);
    }
}
