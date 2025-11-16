import java.util.Scanner;
import java.util.*;

/**
 * Class defining the blackjack solitaire game
 */
public class BlackjackSolitaire {
    /*
     * Set the private fields of the BlackjackSolitaire class
     * Three private fields considers:
     * - deck (Deck): Deck object defining the playing card deck to be played with
     * - board (Board): Board object defining playing and discard boards
     * - sc (Scanner): scanner for getting user input from console for gameplay
     * */
    private Deck deck;
    private Board board;
    static Scanner sc = new Scanner(System.in);

    /**
     * Custom no argument constructor, initializing class fields, and shuffling the deck of cards
     */
    public BlackjackSolitaire() {
        this.deck = new Deck();
        this.deck.shuffleDeck();
        this.board = new Board();
    }

    public Deck getDeck() {
        return deck;
    }

    /**
     * Method to get the final score from the full board
     * @return sum of the total boards current score
     */
    private int sumScore() {

        int gameSum = 0;
        int aceCount = 0;
        Card[][] gameBoard = this.board.getBoard();

        // Row hand scores
        for (int i = 0; i < gameBoard.length; i++) {
            int handScore = 0;
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == null)  { // allow for mud game score report
                    continue;
                } else if (((i == gameBoard.length - 1 || i == gameBoard.length - 2)) && ((j == gameBoard[0].length - 1) || (j == 0))) { // ignore discard pile
                    continue;
                } else if (gameBoard[i][j].getValue() == 1) { // account for aces (automatically make first ace worth 11, and any additional in row worth one, while tracking number of aces in the row)
                    if (aceCount == 0 && handScore <= 10) {
                        aceCount++;
                        handScore += 11;
                    } else {
                        handScore += 1;
                    }
                } else {
                    handScore += gameBoard[i][j].getValue();
                }
            }
            if (handScore > 21 && aceCount >= 1) { // if the score is a bust and we had 1 or more aces, subtract 10 from hand sum to set the first ace value to 1
                handScore -= 10;
            }
            gameSum += scoreHand(handScore, false);
            aceCount = 0;
        }

        // Column hand scores
        for (int j = 0; j < gameBoard[0].length; j++) {
            int handScore = 0;
            for (int i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i][j] == null) { // allow for mud game score report
                    continue;
                } else if (((i == gameBoard.length - 1 || i == gameBoard.length - 2)) && ((j == gameBoard[0].length - 1) || (j == 0))) { // ignore discard pile
                    continue;
                } else if (gameBoard[i][j].getValue() == 1) { // account for aces (automatically make first ace worth 11, and any additional in row worth one, while tracking number of aces in the row)
                    if (aceCount == 0 && handScore <= 10) {
                        aceCount++;
                        handScore += 11;
                    } else {
                        handScore += 1;
                    }
                } else {
                    handScore += gameBoard[i][j].getValue();
                }
            }
            if (handScore > 21 && aceCount >= 1) { // if the score is a bust and we had 1 or more aces, subtract 10 from hand sum to set the first ace value to 1
                handScore -= 10;
            }
            if (j == 0 || j == 4) {
                gameSum += scoreHand(handScore, true);
            } else {
                gameSum += scoreHand(handScore, false);
            }
            aceCount = 0;
        }
        return gameSum;
    }

    /**
     * Method to calculate what the value of a given "hand" is (filled out column
     * or row on the board)
     * @param handScore sum of cards in the provided hand
     * @param blackjack boolean flag indicating whether hand is in 2 card column (eligible for blackjack)
     * @return Point value of hand in game of blackjack solitaire
     */
    private int scoreHand(int handScore, boolean blackjack) {
        if (handScore == 21) {
            if (blackjack) {
                return 10;
            }
            return 7;
        } else if (handScore > 16 && handScore < 21) {
            return handScore - 15;
        } else if (handScore <= 16 && handScore > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Method to check if solitaire board (not including discard slots)
     * is full
     * @return boolean indicating if board has been filled yet or not
     */
    public boolean isBoardFull() {
        Card[][] gameBoard = this.board.getBoard();
        for (int i = 0; i < this.board.length(); i++) {
            for (int j = 0; j < this.board.width(); j++) {
                if (i >= 2) {
                    if (j == 0 || j == 4) {
                        continue;
                    }
                }
                if (gameBoard[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method that carries out the actual gameplay of blackjack solitaire
     */
    public void play() {
        System.out.println("Let's play Blackjack Solitaire! Below you can find the layout of the game:");
        System.out.println();
        System.out.println("Playing Board:");
        this.board.printBoard();
        System.out.println("Discard Board:");
        this.board.printDiscard();
        System.out.println("When you are ready, hit enter to start!\n\n\n"
        );
        sc.nextLine();
        int disCards = 4;
        while (!this.isBoardFull()) {
            Card topCard = deck.getTopCard();
            System.out.println();
            System.out.println();
            System.out.println("Current Score: " + this.sumScore());
            System.out.println("Discards remaining: " + disCards);
            System.out.println("Game Board:");
            this.board.printBoard();
            System.out.println("Discard Board:");
            this.board.printDiscard();
            System.out.println();
            System.out.println("Drawn Card: " + topCard.toString());
            boolean tilePlaced = false;

            do {
                System.out.println("Please select action for drawn card (1-16 on the game board, 17-20 for discard): ");
                int tile = sc.nextInt();
                if (tile >= 1 && tile <= 16) {
                    tilePlaced = this.board.placeCard(tile, topCard);
                } else if (tile >= 17 && tile <= 20) {
                    if (disCards > 0) {
                        tilePlaced = this.board.discardCard(tile, topCard);
                        if (tilePlaced) {
                            disCards--;
                        }
                    } else {
                        tilePlaced = false;
                        System.out.println("No more discards left!");
                    }

                } else {
                    System.out.println("Invalid tile number, please reference board and try again.");
                }

            } while (!tilePlaced);

        }

        int gameScore = this.sumScore();
        System.out.println();
        System.out.println("Final Game Board:");
        this.board.printBoard();
        System.out.println("\nGame has finished!\nGame Score: " + gameScore);



    }

    public static void main(String[] args) {
        BlackjackSolitaire bj = new BlackjackSolitaire();
        HashSet<Card> set = new HashSet<>(bj.getDeck().getCards());
        Card top = bj.getDeck().getTopCard();
        set.remove(top);
        System.out.println(set.size());

    }


}
