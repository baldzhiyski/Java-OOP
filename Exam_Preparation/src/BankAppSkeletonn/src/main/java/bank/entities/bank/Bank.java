package BankAppSkeletonn.src.main.java.bank.entities.bank;

import BankAppSkeletonn.src.main.java.bank.entities.client.Client;
import BankAppSkeletonn.src.main.java.bank.entities.loan.Loan;

import java.util.Collection;

public interface Bank {
    String getName();

    void setName(String name);

    Collection<Client> getClients();

    Collection<Loan> getLoans();

    void addClient(Client client);

    void removeClient(Client client);

    void addLoan(Loan loan);

    int sumOfInterestRates();

    String getStatistics();
}
