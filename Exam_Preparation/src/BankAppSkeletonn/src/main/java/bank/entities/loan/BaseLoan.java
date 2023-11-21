package BankAppSkeletonn.src.main.java.bank.entities.loan;

public abstract class BaseLoan implements Loan {
    private int interestRate;
    private double amount;

    public BaseLoan(int interestRate, double amount) {
        this.interestRate = interestRate;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
