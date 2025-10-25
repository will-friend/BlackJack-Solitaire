/**
 * Class that defines the playing board/grid and the discard
 * board/grid
 */

public class Board {
    /*
     * Set the private field of the Board class
     * One private fields considers:
     * - board (Card[][]): 2D-Array of Card objects
     * */
    private Card[][] board;

    /**
     * Costume no input constructor to initialize board field
     * with proper dimensions
     */
    public Board() {
        board = new Card[4][5];
    }

    /**
     * Method to allow user to get the board field
     * @return current board object state
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
     * Method to get the length of the board
     * @return length of the board
     */
    public int length() {
        return board.length;
    }

    /**
     * Method to get the width of the board
     * @return width of the board
     */
    public int width() {
        return board[0].length;
    }

    /**
     * Method to get the proper column value in 2D-Array board for user provided
     * tile input
     * @param tileNum user inputted tile number
     * @param row calculated row value based on tileNum
     * @return column index in 2D-Array class field board
     */
    private int getColumn(int tileNum, int row) {

        int col = 0;
        if (row < this.board.length / 2) {
            col = ((tileNum-1) % this.board[0].length);
        } else if (row == this.board.length / 2) {
            if (tileNum == 17) {
                col = (10 % this.board[0].length);
            } else if (tileNum == 18) {
                col = (14 % this.board[0].length);
            } else {
                col = (tileNum) % this.board[0].length;
            }
        } else if (row == this.board.length - 1) {
            if (tileNum == 19) {
                col = (15 % this.board[0].length);
            } else if (tileNum == 20) {
                col = (19 % this.board[0].length);
            } else {
                col = (tileNum+2) % this.board[0].length;
            }
        }
        return col;
    }

    /**
     * Method to calculate proper row value given user inputted tile location
     * @param tileNum user inputted tile location on board
     * @return row index value
     */
    private int getRow(int tileNum) {
        int row = 0;
        if (tileNum == 17 || tileNum == 18) {
            row = (tileNum - board[0].length) / board[0].length;
        } else if (tileNum == 14 || tileNum == 15) {
            row = (tileNum + 1) / board[0].length;
        } else {
            row = (tileNum - 1) / this.board[0].length;
        }
        return row;
    }

    /**
     * Method allowing player to place a card on empty space on regular game board
     * @param tileNum tile number player chose to enter
     * @param card card object to be placed onto the board
     * @return boolean flag indicating whether card was placed or not
     */
    public boolean placeCard(int tileNum, Card card) {

        int row = getRow(tileNum);
        int col = getColumn(tileNum, row);

        if ((row == 2 || row == 3) && (col == 0 || col == 4)) {
            System.out.println("Cannot place tile in discard slot, please enter a valid slot.");
            return false;
        }
        if (board[row][col] != null) {
            System.out.println("Card already placed here, please choose another spot.");
            return false;
        } else {
            board[row][col] = card;
            System.out.println("Placed card: " + card);
        }
        return true;
    }

    /**
     * Method allowing player to place a card on empty space in discard board
     * @param tileNum tile number player chose to enter
     * @param card card object to be placed onto the board
     * @return boolean flag indicating whether card was discarded or not
     */
    public boolean discardCard(int tileNum, Card card) {

        int row = getRow(tileNum);
        int col = getColumn(tileNum, row);

        if (board[2][0] != null && board[2][4] != null && board[3][0] != null && board[3][4] != null) {
            System.out.println("No more slots to discard to, please play the card.");
            return false;
        }

        if ((row == 2 || row == 3) && (col == 0 || col == 4)) {
            this.board[row][col] = card;
            return true;
        }
        System.out.println("Invalid entry, please enter a valid discard slot.");
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
                    System.out.print("     ");
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
     * @param args main default args
     */
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println("Board:");
        board.printBoard();
        System.out.println("Discard:");
        board.printDiscard();

        Card c1 = new Card("Heart", 3);
        Card c2 = new Card("Diamond", 2);

        board.placeCard(14, c1);
        board.discardCard(18, c2);
        System.out.println("Board:");
        board.printBoard();
        System.out.println("Discard:");
        board.printDiscard();

    }
}
