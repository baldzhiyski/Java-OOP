package solidExercise.products;

public class Coke implements Drink{

    public static final double CALORIES_PER_100_GRAMS = 44.0;
    public static final double DENSITY = 0.6;

    private double milliliters;

    public Coke(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }

    @Override
    public double amountOfCalories() {
        return (CALORIES_PER_100_GRAMS/100)*(getMilliliters()*DENSITY);
    }

    @Override
    public double amountOfDrink() {
        return milliliters/1000 *DENSITY;
    }
}
