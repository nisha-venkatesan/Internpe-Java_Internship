import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        printBoard();

        while (true) {
            playerMove();
            if (checkWin('X')) {
                printBoard();
                System.out.println("You win!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            computerMove();
            if (checkWin('O')) {
                printBoard();
                System.out.println("Computer wins!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static void playerMove() {
        while (true) {
            System.out.print("Enter your move (row and column 0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = 'X';
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        printBoard();
    }

    static void computerMove() {
        System.out.println("Computer's move:");
        
        // Try to win
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    if (checkWin('O')) return;
                    board[i][j] = ' ';
                }

        // Block player from winning
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') {
                    board[i][j] = 'X';
                    if (checkWin('X')) {
                        board[i][j] = 'O';
                        printBoard();
                        return;
                    }
                    board[i][j] = ' ';
                }

        // Otherwise pick first empty
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    printBoard();
                    return;
                }
    }

    static boolean checkWin(char player) {
        // Rows, columns, diagonals
        for (int i = 0; i < 3; i++)
            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) return true;

        for (int j = 0; j < 3; j++)
            if (board[0][j] == player &&
                board[1][j] == player &&
                board[2][j] == player) return true;

        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) return true;

        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) return true;

        return false;
    }

    static boolean isBoardFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }
}

