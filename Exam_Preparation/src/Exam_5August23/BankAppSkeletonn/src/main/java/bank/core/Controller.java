package Exam_5August23.BankAppSkeletonn.src.main.java.bank.core;

public interface Controller {

    String addBank(String type, String name);
    String addLoan(String type);
    String returnedLoan(String bankName, String loanType);
    String addClient(String bankName, String clientType, String clientName, String clientID, double income);
    String finalCalculation(String bankName);
    String getStatistics();
}
