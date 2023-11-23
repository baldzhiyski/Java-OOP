package Exam_5August23.BankAppSkeletonn.src.main.java.bank.entities.client;

import Exam_5August23.BankAppSkeletonn.src.main.java.bank.common.ExceptionMessages;

public abstract class BaseClient implements Client{
    private String name;
    private String ID;
    private int interest;
    private double income;

    public BaseClient(String name, String ID, int interest, double income) {
        setName(name);
        setID(ID);
        this.interest = interest;
        setIncome(income);
    }

    @Override
    public void setName(String name) {
        if(name.isBlank()){
            throw new IllegalArgumentException(ExceptionMessages.CLIENT_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setID(String ID) {
        if(name.isBlank()){
            throw new IllegalArgumentException(ExceptionMessages.CLIENT_ID_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.ID = ID;
    }

    public void setIncome(double income) {
        if(income<=0){
            throw new IllegalArgumentException(ExceptionMessages.CLIENT_INCOME_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        }
        this.income = income;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getIncome() {
        return income;
    }

    @Override
    public int getInterest() {
        return interest;
    }

    @Override
    public void increase() {
        this.interest+=1;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }


}
