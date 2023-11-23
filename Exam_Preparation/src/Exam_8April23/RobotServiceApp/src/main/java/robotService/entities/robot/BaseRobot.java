package Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot;

import Exam_8April23.RobotServiceApp.src.main.java.robotService.common.ExceptionMessages;

public class BaseRobot implements Robot{
    private String name;
    private String kind;
    private int kilograms;
    private double price;

    public BaseRobot(String name, String kind, int kilograms, double price) {
        setName(name);
        setKind(kind);
        setKilograms(kilograms);
        setPrice(price);
    }

    public void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if(name.isBlank()){
            throw new NullPointerException(ExceptionMessages.ROBOT_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name=name;
    }

    public void setKind(String kind) {
        if(kind.isBlank()){
            throw new NullPointerException(ExceptionMessages.ROBOT_KIND_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setPrice(double price) {
        if(price<=0){
            throw new IllegalArgumentException(ExceptionMessages.ROBOT_PRICE_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        }
        this.price = price;
    }

    @Override
    public int getKilograms() {
        return kilograms;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void eating() {
        this.kilograms+=1;
    }
}
