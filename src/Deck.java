import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
        for (int i = 0; i < 52; i++) {
            int rank = (i % 13) + 1;
            int suit = i / 13;
            if (suit == 0) {
                cards.add(new Card("Heart", rank));
            } else if (suit == 1) {
                cards.add(new Card("Spade", rank));
            } else if (suit == 2) {
                cards.add(new Card("Club", rank));
            } else {
                cards.add(new Card("Diamond", rank));
            }
        }
    }

    public void shuffleDeck() {
        
    }
}
