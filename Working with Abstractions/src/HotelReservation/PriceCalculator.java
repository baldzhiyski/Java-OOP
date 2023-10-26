package HotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private  Discount discount;

    public PriceCalculator(double pricePerDay, int numberOfDays, Season season, Discount discount) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discount = discount;
    }
    public double calculatePrice(){
        return pricePerDay*numberOfDays*season.getMultiplier()*discount.getPriceReductionFactor();
    }
}
