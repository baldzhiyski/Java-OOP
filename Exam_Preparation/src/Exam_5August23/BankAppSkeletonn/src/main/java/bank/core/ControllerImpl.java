package Exam_5August23.BankAppSkeletonn.src.main.java.bank.core;

import Exam_5August23.BankAppSkeletonn.src.main.java.bank.common.ConstantMessages;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.common.ExceptionMessages;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.bank.Bank;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.bank.BranchBank;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.bank.CentralBank;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.client.Adult;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.client.Client;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.client.Student;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.loan.Loan;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.loan.MortgageLoan;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.loan.StudentLoan;
import Exam_5August23.BankAppSkeletonn.src.main.java.bank.repositories.LoanRepository;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private LoanRepository loans;
    private Map<String, Bank> banks;

    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks= new LinkedHashMap<>();
    }

    @Override
    public String addBank(String type, String name) {
        Bank bank;
        switch (type){
            case "BranchBank":
                bank= new BranchBank(name);
                break;
            case "CentralBank":
                bank= new CentralBank(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_BANK_TYPE);
        }
        banks.put(name,bank);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE,bank.getClass().getSimpleName());
    }
    @Override
    public String addLoan(String type) {
        Loan loan;
        switch (type){
            case "StudentLoan":
                loan= new StudentLoan();
                break;
            case "MortgageLoan":
                loan= new MortgageLoan();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_LOAN_TYPE);
        }
       loans.addLoan(loan);
      return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE,loan.getClass().getSimpleName());
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan first = loans.findFirst(loanType);
        if(first==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND,loanType));
        }
        loans.removeLoan(first);
        Bank bank1 = banks.get(bankName);
        bank1.addLoan(first);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK,loanType,bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Client client;
        switch (clientType){
            case "Adult":
                client= new Adult(clientName,clientID,income);
                break;
            case "Student":
                client= new Student(clientName,clientID,income);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }
        Bank bank1 = banks.get(bankName);
        if((client.getClass().getSimpleName().equals("Adult") && !bank1.getClass().getSimpleName().equals("CentralBank"))
        || (client.getClass().getSimpleName().equals("Student") && !bank1.getClass().getSimpleName().equals("BranchBank"))){
            return ConstantMessages.UNSUITABLE_BANK;
        }
        bank1.addClient(client);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK,clientType,bankName);
    }

    @Override
    public String finalCalculation(String bankName) {
        double funds = 0;
        Bank bank1 = banks.get(bankName);
        for (Client client : bank1.getClients()) {
            funds+=client.getIncome();
        }
        for (Loan loan : bank1.getLoans()) {
            funds+=loan.getAmount();
        }
        return String.format(ConstantMessages.FUNDS_BANK,bankName,funds);
    }

    @Override
    public String getStatistics() {
        return banks.values()
                .stream()
                .map(Bank::getStatistics)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
