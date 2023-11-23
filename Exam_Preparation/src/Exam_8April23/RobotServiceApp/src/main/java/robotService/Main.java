package Exam_8April23.RobotServiceApp.src.main.java.robotService;


import Exam_8April23.RobotServiceApp.src.main.java.robotService.core.Engine;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.core.EngineImpl;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
