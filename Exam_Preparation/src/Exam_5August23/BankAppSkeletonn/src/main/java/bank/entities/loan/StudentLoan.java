package Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.loan;

public class StudentLoan extends BaseLoan{
    private static final int INTEREST_RATE =1;
    private static final double AMOUNT = 10000;
    public StudentLoan() {
        super(INTEREST_RATE, AMOUNT);
    }
}
