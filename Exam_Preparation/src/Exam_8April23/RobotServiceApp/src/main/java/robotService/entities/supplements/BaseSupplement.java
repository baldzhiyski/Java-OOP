package Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements;

public class BaseSupplement implements Supplement{
    private int hardness;
    private double price;

    public BaseSupplement(int hardness, double price) {
        this.hardness = hardness;
        this.price = price;
    }

    @Override
    public int getHardness() {
        return hardness;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
