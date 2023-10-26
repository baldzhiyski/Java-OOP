package HotelReservation;

public enum Season {
    SPRING(2),
    WINTER(3),
    SUMMER(4),
    AUTUMN(1);

    private int multiplier;

    Season(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
    public static Season parse(String str){
        return Season.valueOf(str.toUpperCase());
    }
}
