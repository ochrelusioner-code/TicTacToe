import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) {


         /* PSEUDOCODE OUTLINE:
         * * SET playing to TRUE
         * * WHILE playing is TRUE DO
         * CALL clearBoard()
         * SET currentPlayer to "X"
         * SET gameFinished to FALSE
         * SET moveCount to 0
         * * WHILE gameFinished is FALSE DO
         * CALL display()
         * * DO
         * OUTPUT "Player " + currentPlayer + ", enter your row move (1-3)"
         * INPUT row
         * OUTPUT "Player " + currentPlayer + ", enter your col move (1-3)"
         * INPUT col
         * * SET row to row - 1
         * SET col to col - 1
         * * SET validMove to CALL isValidMove(row, col)
         * * IF validMove is FALSE THEN
         * OUTPUT "That space is already taken!"
         * END IF
         * WHILE validMove is FALSE
         * * SET board[row][col] to currentPlayer
         * SET moveCount to moveCount + 1
         * * IF moveCount >= 5 THEN
         * IF CALL isWin(currentPlayer) is TRUE THEN
         * CALL display()
         * OUTPUT "PLAYER " + currentPlayer + " WINS!"
         * SET gameFinished to TRUE
         * ELSE IF moveCount >= 7 AND CALL isTie() is TRUE THEN
         * CALL display()
         * OUTPUT "It's a TIE!"
         * SET gameFinished to TRUE
         * END IF
         * END IF
         * * IF gameFinished is FALSE THEN
         * IF currentPlayer equals "X" THEN
         * SET currentPlayer to "O"
         * ELSE
         * SET currentPlayer to "X"
         * END IF
         * END IF
         * * END WHILE
         * * OUTPUT "Would you like to play again? (Y/N)"
         * INPUT playing
         * * END WHILE
         * OUTPUT "Thanks for playing!"
         */

        Scanner in = new Scanner(System.in);
        boolean playing = true;

        System.out.println("Welcome to Tic Tac Toe!");

        while (playing) {
            clearBoard();
            String currentPlayer = "X";
            boolean gameFinished = false;
            int moveCount = 0;

            while (!gameFinished) {
                display();

                int row, col;
                boolean validMove;

                do {
                    row = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter your row move", 1, 3);
                    col = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter your column move", 1, 3);

                    row = row - 1;
                    col = col - 1;

                    validMove = isValidMove(row, col);

                    if (!validMove) {
                        System.out.println("\n*** That space is already taken! Please choose an empty space. ***\n");
                    }
                } while (!validMove);

                board[row][col] = currentPlayer;
                moveCount++;

                if (moveCount >= 5) {
                    if (isWin(currentPlayer)) {
                        display();
                        System.out.println("=====================================");
                        System.out.println("PLAYER " + currentPlayer + " WINS! Congratulations!");
                        System.out.println("=====================================");
                        gameFinished = true;
                    }
                    else if (moveCount >= 7 && isTie()) {
                        display();
                        System.out.println("=====================================");
                        System.out.println("It's a TIE! The board is deadlocked.");
                        System.out.println("=====================================");
                        gameFinished = true;
                    }
                }

                if (!gameFinished) {
                    if (currentPlayer.equals("X")) {
                        currentPlayer = "O";
                    } else {
                        currentPlayer = "X";
                    }
                }
            }

            playing = SafeInput.getYNConfirm(in, "Would you like to play again?");
        }

        System.out.println("Thanks for playing!");
        in.close();
    }

    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static void display() {
        System.out.println();
        for (int r = 0; r < ROWS; r++) {
            System.out.print(" ");
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c]);
                if (c < COLS - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (r < ROWS - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        boolean xFlag, oFlag;
        int blockedVectors = 0;

        for (int r = 0; r < ROWS; r++) {
            xFlag = false; oFlag = false;
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals("X")) xFlag = true;
                if (board[r][c].equals("O")) oFlag = true;
            }
            if (xFlag && oFlag) blockedVectors++;
        }

        for (int c = 0; c < COLS; c++) {
            xFlag = false; oFlag = false;
            for (int r = 0; r < ROWS; r++) {
                if (board[r][c].equals("X")) xFlag = true;
                if (board[r][c].equals("O")) oFlag = true;
            }
            if (xFlag && oFlag) blockedVectors++;
        }

        xFlag = false; oFlag = false;
        if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) xFlag = true;
        if (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) oFlag = true;
        if (xFlag && oFlag) blockedVectors++;

        xFlag = false; oFlag = false;
        if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) xFlag = true;
        if (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O")) oFlag = true;
        if (xFlag && oFlag) blockedVectors++;

        if (blockedVectors == 8) {
            return true;
        }

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }
}