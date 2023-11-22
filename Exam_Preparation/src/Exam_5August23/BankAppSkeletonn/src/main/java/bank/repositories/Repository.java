package BankAppSkeletonn.src.main.java.bank.repositories;

import BankAppSkeletonn.src.main.java.bank.entities.loan.Loan;

public interface Repository {

    void addLoan(Loan loan);

    boolean removeLoan(Loan loan);

    Loan findFirst(String type);
}
