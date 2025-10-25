/**
 * Card class defines the properties of a playing card in a standard 52-card deck
 * of cards
 */
public class Card {
    /*
    * Set the private fields of the Card class
    * Four private fields considers:
    * - suit (String): The suit of the card
    * - value (int): The value the card holds
    * - played (boolean): boolean indicating whether card ahs been played or not
    * - rank (int): Rank of card in the deck (Ace has rank 1, King has rank 13)
    * */
    private final String suit;
    private int value;
    private boolean played;
    private final int rank;

    /**
     * Two input constructor, taking the suit and the rank of the card
     * @param suit suit (Heart, club, spade, diamond) of the playing card
     * @param rank rank of the card in the deck, with ace=1 and king=13
     */
    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        // Set value of card: if it is 10 or a face card, set value to 10
        // We initialize an Ace to value 1 for now
        if (this.rank >= 10) {
            this.value = 10;
        } else {
            this.value = rank;
        }
    }

    /**
     * Allow user to retrieve value of the card
     * @return Default value of the card
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Set card status to true, indicating it has been placed
     * @param played boolean indicating whether a card has been played or not
     */
    public void setPlayed(boolean played) {
        this.played = played;
    }

    /**
     * Reassign ace to alternate value of 11
     */
    public void assignAceMax() {
        if (this.rank == 1) {
            this.value = 11;
        }
    }

    /**
     * Custom toString method to allow for easily readable display of game state
     * @return string representation of playing card
     */
    public String toString() {
        if (this.rank < 10 && this.rank > 1) {
            return this.rank + this.suit.substring(0,1);
        } else if (this.rank == 1) {
            return "A" + this.suit.charAt(0);
        } else if (this.rank == 11) {
            return "J" + this.suit.charAt(0);
        } else if (this.rank == 12) {
            return "Q" + this.suit.charAt(0);
        } else {
            return "K" + this.suit.charAt(0);
        }
    }

    /**
     * test bed
     * @param args default main arguments
     */
    public static void main(String[] args) {
        Card c1 = new Card("Heart", 1);
        Card c2 = new Card("Club", 12);
        Card c3 = new Card("Diamond", 4);
        Card c4 = new Card("Spade", 11);
        Card c5 = new Card("Club", 1);
        c5.assignAceMax();

        int expectedC1Value = 1;
        int expectedC2Value = 10;
        int expectedC3Value = 4;
        int expectedC4Value = 10;
        int expectedC5Value = 11;

        int actualC1Value = c1.value;
        int actualC2Value = c2.value;
        int actualC3Value = c3.value;
        int actualC4Value = c4.value;
        int actualC5Value = c5.value;

        Test(expectedC1Value==actualC1Value);
        Test(expectedC2Value==actualC2Value);
        Test(expectedC3Value==actualC3Value);
        Test(expectedC4Value==actualC4Value);
        Test(expectedC5Value==actualC5Value);

        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(c3.toString());
        System.out.println(c4.toString());
        System.out.println(c5.toString());

    }

    public static void Test(boolean test) {
        if (test) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }
}

