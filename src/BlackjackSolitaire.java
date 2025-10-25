import java.util.Scanner;

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

    /**
     * Method to get the final score from the full board
     * @return
     */
    private int sumScore() {

        int gameSum = 0;
        int aceCount = 0;
        Card[][] gameBoard = board.getBoard();

        // Row hand scores
        for (int i = 0; i < gameBoard.length; i++) {
            int handScore = 0;
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == null)  {
                    continue;
                } else if (((i == gameBoard.length - 1 || i == gameBoard.length - 2)) && ((j == gameBoard[0].length - 1) || (j == 0))) {
                    continue;
                } else if (gameBoard[i][j].getValue() == 1) {
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
            if (handScore > 21 && aceCount >= 1) {
                handScore -= 10;
            }
            gameSum += scoreHand(handScore, false);
            aceCount = 0;
        }

        // Column hand scores
        for (int j = 0; j < gameBoard[0].length; j++) {
            int handScore = 0;
            for (int i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i][j] == null) {
                    continue;
                } else if (((i == gameBoard.length - 1 || i == gameBoard.length - 2)) && ((j == gameBoard[0].length - 1) || (j == 0))) {
                    continue;
                } else if (gameBoard[i][j].getValue() == 1) {
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
            if (handScore > 21 && aceCount >= 1) {
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
        } else if (handScore <= 16) {
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
        System.out.println("\nThere are two boards above: the Playing Board, and the Discard Board. Each board is \n" +
                "labeled with a number indicating a position on either board. When presented with a new card, \n" +
                "a player has two choice: to play the card, or to discard the card. A player must (when prompted)\n" +
                "the space they want to play (or discard) the card too. Depending on the action chosen, the game\n" +
                "will either confirm that the card can be played, or inform the player that there move is invalid \n" +
                "and they must choose a new action. The goal of the game is to place (or discard) each new card \n" +
                "presented, trying to get a sum of 21 on any row or column of the game board. The game ends only\n" +
                "when the board is completely filled. A player gets a total of four discards per game (represented \n" +
                "by the 2x2 discard grid). When you are ready, hit enter to start!\n\n\n"
        );
        sc.nextLine();
        int disCards = 4;
        while (!this.isBoardFull()) {
            Card topCard = deck.getTopCard();
            System.out.println();
            System.out.println("Drawn Card: " + topCard.toString());
            System.out.println("Game Board:");
            board.printBoard();
            System.out.println("Discard Board:");
            board.printDiscard();

            boolean tilePlaced = false;

            do {
                System.out.println("Please select the tile you would like to play the drawn card on (1-16 on the game board, 17-20 for discard): ");
                int tile = sc.nextInt();
                if (tile >= 1 && tile <= 16) {
                    tilePlaced = board.placeCard(tile, topCard);
                } else if (tile >= 17 && tile <= 20) {
                    tilePlaced = board.discardCard(tile, topCard);
                    if (tilePlaced) {
                        disCards--;
                        System.out.println("Discards remaining: " + disCards);
                    }
                } else {
                    System.out.println("Invalid tile number, please reference board and try again.");
                }
                System.out.println();

            } while (!tilePlaced);

        }

        int gameScore = sumScore();

        System.out.println("Final Game Board:");
        board.printBoard();
        System.out.println("\nGame has finished!\nGame Score: " + gameScore);



    }


}
