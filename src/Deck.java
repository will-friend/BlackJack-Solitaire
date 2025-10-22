import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
        for (int i = 0; i < 52; i++) {
            int rank = (i % 13) + 1;
            int suit = i / 13;
            if (suit == 0) {
                this.cards.add(new Card("Heart", rank));
            } else if (suit == 1) {
                this.cards.add(new Card("Spade", rank));
            } else if (suit == 2) {
                this.cards.add(new Card("Club", rank));
            } else {
                this.cards.add(new Card("Diamond", rank));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void shuffleDeck() {
        Random random = new Random(123);
        Collections.shuffle(this.cards, random);
    }

    public Card getTopCard() {
        Card topCard = this.cards.getLast();
        topCard.setPlayed(true);
        return topCard;
    }

}
