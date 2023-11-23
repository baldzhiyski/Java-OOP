package Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.client;

public class Adult extends BaseClient{
    private static final int INTEREST = 4;
    public Adult(String name, String ID, double income) {
        super(name, ID, INTEREST, income);
    }
    @Override
    public void increase() {
        super.increase();
        setInterest(getInterest()+1);
    }
}
