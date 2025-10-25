import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackSolitaire {

    private Deck deck;
    private Board board;
    static Scanner sc = new Scanner(System.in);

    public BlackjackSolitaire() {
        this.deck = new Deck();
        this.deck.shuffleDeck();
        this.board = new Board();
    }

    public int sumScore() {
        int gameSum = 0;
        int aceCount = 0;
        Card[][] gameBoard = board.getBoard();
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

    public boolean isBoardFull() {
        Card[][] gameBoard = this.board.getBoard();
        for (int i = 0; i < this.board.length(); i++) {
            for (int j = 0; j < this.board.width(); j++) {
                if ((i < 2 && (j > 0 && j < 4)) && gameBoard[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void play() {
        System.out.println("Let's play Blackjack Solitaire! Below you can find the layout of the game:");
        System.out.println();
        System.out.println("Playing Board:");
        this.board.printBoard();
        System.out.println("Discard Board:");
        this.board.printDiscard();
        System.out.println("There are two boards above: the Playing Board, and the Discard Board. Each board is \n" +
                "labeled with a number indicating a position on either board. When presented with a new card, a player\n" +
                "has two choice: to play the card, or to discard the card. A player must (when prompted) the space they\n" +
                " want to play (or discard) the card too. Depending on the action chosen, the game will either \n" +
                "confirm that the card can be played, or inform the player that there move is invalid and they must choose \n" +
                "a new action.\n" +
                "The goal of the game is to place (or discard) each new card presented, trying to get a sum of 21 on any row or \n" +
                "column of the game board. The game ends only when the board is completely filled. A player gets a total of four\n" +
                "discards per game (represented by the 2x2 discard grid). Let's start\n\n\n"
        );
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

        System.out.println("Game has finished!\nGame Score: " + gameScore);



    }


}
