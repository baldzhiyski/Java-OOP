package solidExercise;

import solidExercise.products.Cloud;
import solidExercise.products.QuantityCalculator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Cloud cloud = new Cloud();
        QuantityCalculator.average(List.of(cloud));
    }
}
