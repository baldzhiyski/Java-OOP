package Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.bank;

import Exam_5August23.BankAppSkeletonn.src.main.java.bank.common.ExceptionMessages;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.client.Client;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseBank implements Bank{
    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;

    public BaseBank(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.loans=new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if(name.isBlank()){
            throw new IllegalArgumentException(ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Client> getClients() {
        return clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return loans;
    }

    @Override
    public void addClient(Client client) {
        if(this.clients.size()==capacity){
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }
        clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        return loans.stream().mapToInt(Loan::getInterestRate).sum();
    }

    @Override
    public String getStatistics() {
        StringBuilder result = new StringBuilder();
        result.append("Name: ").append(name).append(", Type: ").append(getClass().getSimpleName()).append(System.lineSeparator());
        result.append("Clients: ");
        String toAdd =clients.isEmpty()?"none": clients.stream().map(Client::getName).collect(Collectors.joining(", "));
        result.append(toAdd).append(System.lineSeparator());
        result.append("Loans: ").append(loans.size()).append(", Sum of interest rates: ").append(sumOfInterestRates());
        return result.toString();
    }

}
