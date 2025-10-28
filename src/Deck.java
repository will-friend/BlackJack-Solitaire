import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *  Class that defines a regular deck of cards, and actions that can
 *  be taken with the deck
 */
public class Deck {
    /*
     * Set the private field of the Deck class
     * One private fields considers:
     * - cards (ArrayList<Card>): Array list of Card objects
     * */
    private ArrayList<Card> cards;

    /**
     * Constructor that will initialize the cards arraylist, and iteratively
     * populate it with the 52 Card objects representing the 52 cards found
     * in a standard playing deck
     */
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

    /**
     * Method to allow user to get the current state of the cards field
     *
     * @return The current state of the cards field
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Method to shuffle elements of card field in place, emulating the
     * shuffling of a deck of cards in real life
     */
    public void shuffleDeck() {
//        Random random = new Random(123);
//        Collections.shuffle(this.cards, random);
        Collections.shuffle(this.cards);
    }

    /**
     * Allow user to get the last element in the cards field, emulating
     * drawing the top card from a deck of cards
     * @return Last element from the cards field of Deck object
     */
    public Card getTopCard() {
        Card topCard = this.cards.getLast();
        this.cards.remove(topCard);
        topCard.setPlayed(true);
        return topCard;
    }

    /**
     * Test bed
     * @param args default main args
     */
    public static void main(String[] args) {

        Deck deck = new Deck();
        ArrayList<Card> cards = deck.getCards();

        System.out.println("Pre-Shuffle deck");
        for (Card card : cards) {
            System.out.println(card.toString());
        }

        deck.shuffleDeck();
        System.out.println("Post-Shuffle deck");
        cards = deck.getCards();
        for (Card card : cards) {
            System.out.println(card.toString());
        }
        System.out.println("Top card: " + deck.getTopCard());
    }

}
