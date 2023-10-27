package Exercise.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String rank = scan.nextLine();
        String cardSuit = scan.nextLine();

        Card card = new Card(CardSuits.valueOf(cardSuit), CardRank.valueOf(rank));
        System.out.printf("Card name: %s of %s; Card power: %d",rank,cardSuit,
                card.getPowerOfCard());
    }
}
