/**
 * Class that defines the playing board/grid and the discard
 * board/grid
 */

public class Board {
    private Card[][] board;

    /**
     * Costume no input constructor to initialize board field
     * with proper dimensions
     */
    public Board() {
        board = new Card[4][5];
    }

    public int length() {
        return board.length;
    }

    public int width() {
        return board[0].length;
    }

    /**
     * Method to allow user to get the board field
     * @return current board object state
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
     * Method allowing player to place a card on empty space on regular game board
     * @param row row index of board
     * @param col column index of board
     * @param card card object to be placed onto the board
     * @return boolean flag indicating whether card was placed or not
     */
    public boolean placeCard(int row, int col, Card card) {
        if ((row == 2 || row == 3) && (col == 0 || col == 4)) {
            System.out.println("Cannot place tile in discard slot, please enter a valid slot.");
            return false;
        }
        if (board[row][col] == null) {
            board[row][col] = card;
            System.out.println("Placed card: " + card);
        }
        return true;
    }

    /**
     * Method allowing player to place a card on empty space in discard board
     * @param row row index of board
     * @param col column index of board
     * @param card card object to be placed onto the board
     * @return boolean flag indicating whether card was discarded or not
     */
    public boolean discardCard(int row, int col, Card card) {

        if (board[2][0] != null && board[2][4] != null && board[3][0] != null && board[3][4] != null) {
            System.out.println("No more slots to discard to, please play the card.");
            return false;
        }

        if ((row == 2 || row == 3) && (col == 0 || col == 4)) {
            this.board[row][col] = card;
            return true;
        }
        System.out.println("Cannot discard a card to a playable slot, please enter a valid discard slot.");
        return false;
    }

    /**
     * Method to display the current game board to the console
     */
    public void printBoard() {
        int count = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i == 2 || i == 3) && (j == 0 || j == 4)) {
                    System.out.print("      ");
                } else {
                    if (board[i][j] != null) {
                        System.out.print(board[i][j].toString()+"   ");
                    } else {
                        System.out.printf("%2d   ", count);
                    }
                    count++;
                }
            }
            System.out.println();
        }
    }

    /**
     * method to display discard grid to console
     */
    public void printDiscard() {
        int[][] discardIdx = {{10, 14}, {15, 19}};
        int tile_print = 17;
        for (int[] row : discardIdx) {
            for (int col : row) {
                int i = col / board[0].length;
                int j = col % board[0].length;
                if (board[i][j] != null) {
                    System.out.print(board[i][j].toString()+"   ");
                } else {
                    System.out.printf("%2d   ", tile_print);
                }
                tile_print++;
            }
            System.out.println();
        }
    }

    /**
     * testing bed
     * @param args
     */
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println("Board:");
        board.printBoard();
        System.out.println("Discard:");
        board.printDiscard();

        Card c1 = new Card("Heart", 3);
        Card c2 = new Card("Diamond", 2);

        board.placeCard(0,0, c1);
        board.discardCard(2, 0, c2);
        System.out.println("Board:");
        board.printBoard();
        System.out.println("Discard:");
        board.printDiscard();

    }
}
