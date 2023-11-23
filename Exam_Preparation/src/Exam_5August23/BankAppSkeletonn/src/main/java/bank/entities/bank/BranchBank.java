package Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.bank;

public class BranchBank extends BaseBank {
    private static final int CAPACITY = 25;

    public BranchBank(String name) {
        super(name, CAPACITY);
    }
}
