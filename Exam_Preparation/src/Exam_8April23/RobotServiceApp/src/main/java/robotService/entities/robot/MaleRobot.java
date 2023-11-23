package Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot;

public class MaleRobot extends BaseRobot{
    private static final int KILOGRAMS=9;
    public MaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        super.eating();
        setKilograms(getKilograms()+2);
    }
}
