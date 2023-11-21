package BankAppSkeletonn.src.main.java.bank;

import BankAppSkeletonn.src.main.java.bank.core.Engine;
import BankAppSkeletonn.src.main.java.bank.core.EngineImpl;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
