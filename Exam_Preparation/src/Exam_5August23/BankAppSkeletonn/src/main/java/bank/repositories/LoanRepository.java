package BankAppSkeletonn.src.main.java.bank.repositories;

import BankAppSkeletonn.src.main.java.bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;

public  class LoanRepository implements Repository{
    private Collection<Loan> loans;

    public LoanRepository() {
        this.loans = new ArrayList<>();
    }

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public boolean removeLoan(Loan loan) {
        for (Loan loan1 : loans) {
            if(loan1.equals(loan)){
                loans.remove(loan);
                return true;
            }
        }
        return false;
    }

    @Override
    public Loan findFirst(String type) {
        return loans.stream()
                .filter(loan -> loan.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }

    public Collection<Loan> getLoans() {
        return loans;
    }
}
