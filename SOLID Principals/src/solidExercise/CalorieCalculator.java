package solidExercise;

import solidExercise.products.Product;

import java.util.List;

// Util Class
public class CalorieCalculator {

    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";

    private CalorieCalculator() {
    }

    public static double sum(List<Product> products) {
        return products.stream().mapToDouble(Product::amountOfCalories).sum();
    }
    public static double average(List<Product> products) {
        return sum(products) / products.size();
    }

}
