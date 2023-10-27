package Exercise.CardsWithPower;
public class Card {
    private CardRank cardRank;
    private CardSuits cardSuit;

    public Card(CardSuits cardSuit , CardRank cardRank) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }
    public int getPowerOfCard(){
        return this.cardRank.getValue() + this.cardSuit.getValue();
    }
}
