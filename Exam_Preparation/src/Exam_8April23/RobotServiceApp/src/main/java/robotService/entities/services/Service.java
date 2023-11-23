package Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.services;

import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot.Robot;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements.Supplement;

import java.util.Collection;

public interface Service {
    String getName();

    void setName(String name);

    Collection<Robot> getRobots();

    Collection<Supplement> getSupplements();

    void addRobot(Robot robot);

    void removeRobot(Robot robot);

    void addSupplement(Supplement supplement);

    void feeding();

    int sumHardness();

    String getStatistics();
}
