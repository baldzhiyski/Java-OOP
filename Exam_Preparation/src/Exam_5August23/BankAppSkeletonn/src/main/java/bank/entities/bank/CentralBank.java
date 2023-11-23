package Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.bank;

public class CentralBank extends BaseBank{
    private static final int CAPACITY = 50;
    public CentralBank(String name) {
        super(name, CAPACITY);
    }
}
