package Exam_15August23.HandballSkeleton.src.main.java.handball;

import Exam_15August23.HandballSkeleton.src.main.java.handball.core.Engine;
import Exam_15August23.HandballSkeleton.src.main.java.handball.core.EngineImpl;


public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
