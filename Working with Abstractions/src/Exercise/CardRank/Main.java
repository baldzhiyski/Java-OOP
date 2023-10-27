package Exercise.CardRank;

public class Main {
    public static void main(String[] args) {
        CardRank[] cardRank = CardRank.values();
        System.out.println("Card Ranks:");
        for (CardRank cardRank1: cardRank) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",cardRank1.ordinal(),cardRank1.name());
        }

    }
}
