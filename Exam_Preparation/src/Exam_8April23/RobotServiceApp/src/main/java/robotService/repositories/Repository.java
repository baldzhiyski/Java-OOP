package Exam_8April23.RobotServiceApp.src.main.java.robotService.repositories;

import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements.Supplement;


public interface Repository {

    void addSupplement(Supplement supplement);

    boolean removeSupplement(Supplement supplement);

    Supplement findFirst(String type);
}
