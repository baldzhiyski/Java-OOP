package HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] arr = scan.nextLine().split("\\s+");
        double price = Double.parseDouble(arr[0]);
        int days = Integer.parseInt(arr[1]);
        Season season = Season.parse(arr[2]);
        Discount discount = Discount.parse(arr[3]);
        PriceCalculator priceCalculator = new PriceCalculator(price,days,season,discount);

        System.out.printf("%.2f",priceCalculator.calculatePrice());
    }
}
