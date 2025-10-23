public class Board {
    private Card[][] board;

    public Board() {
        board = new Card[4][5];
    }

    public Card[][] getBoard() {
        return board;
    }

    public boolean placeCard(int row, int col, Card card) {
        if ((row == 2 || row == 3) && (col == 0 || col == 4)) {
            System.out.println("Cannot place tile in discard slot, please enter a valid slot.");
            return false;
        }
        this.board[row][col] = card;
        return true;
    }

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

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i == 2 || i == 3) && (j == 0 || j == 4)) {
                    System.out.print("      ");
                } else {
                    if (board[i][j] != null) {
                        System.out.print(board[i][j].toString()+"   ");
                    } else {
                        System.out.printf("%2d   ", (board[i].length*i) + j);
                    }
                }
            }
            System.out.println();
        }
    }

    public void printDiscard() {
        int[][] discardIdx = {{10, 14}, {15, 19}};
        for (int[] row : discardIdx) {
            for (int col : row) {
                int i = col / board[0].length;
                int j = col % board[0].length;
                if (board[i][j] != null) {
                    System.out.print(board[i][j].toString()+"   ");
                } else {
                    System.out.printf("%2d   ", col);
                }
            }
            System.out.println();
        }
    }

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
