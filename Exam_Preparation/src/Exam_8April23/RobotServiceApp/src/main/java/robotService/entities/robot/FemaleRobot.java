package Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot;

public class FemaleRobot extends BaseRobot{
    private static final int KILOGRAMS = 7;
    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }
}
