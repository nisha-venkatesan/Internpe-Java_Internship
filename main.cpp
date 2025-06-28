#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>

using namespace std;

const int ROWS = 6;
const int COLS = 7;
const char EMPTY = ' ';
const char PLAYER = 'X';
const char COMPUTER = 'O';

void printBoard(const vector<vector<char>>& board) {
    for (int i = 0; i < ROWS; ++i) {
        for (int j = 0; j < COLS; ++j) {
            cout << "| " << board[i][j] << " ";
        }
        cout << "|\n";
    }
    cout << "-----------------------------\n";
    cout << "| 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n";
    cout << "-----------------------------\n";
}

bool isValidMove(const vector<vector<char>>& board, int col) {
    return col >= 0 && col < COLS && board[0][col] == EMPTY;
}

void makeMove(vector<vector<char>>& board, int col, char symbol) {
    for (int row = ROWS - 1; row >= 0; --row) {
        if (board[row][col] == EMPTY) {
            board[row][col] = symbol;
            break;
        }
    }
}

bool checkWin(const vector<vector<char>>& board, char symbol) {
    // Horizontal, vertical, diagonal
    for (int r = 0; r < ROWS; ++r) {
        for (int c = 0; c < COLS; ++c) {
            if (c + 3 < COLS &&
                board[r][c] == symbol && board[r][c+1] == symbol &&
                board[r][c+2] == symbol && board[r][c+3] == symbol) return true;
            if (r + 3 < ROWS &&
                board[r][c] == symbol && board[r+1][c] == symbol &&
                board[r+2][c] == symbol && board[r+3][c] == symbol) return true;
            if (r + 3 < ROWS && c + 3 < COLS &&
                board[r][c] == symbol && board[r+1][c+1] == symbol &&
                board[r+2][c+2] == symbol && board[r+3][c+3] == symbol) return true;
            if (r + 3 < ROWS && c - 3 >= 0 &&
                board[r][c] == symbol && board[r+1][c-1] == symbol &&
                board[r+2][c-2] == symbol && board[r+3][c-3] == symbol) return true;
        }
    }
    return false;
}

bool isFull(const vector<vector<char>>& board) {
    for (int c = 0; c < COLS; ++c)
        if (board[0][c] == EMPTY) return false;
    return true;
}

int getComputerMove(const vector<vector<char>>& board) {
    vector<int> validCols;
    for (int i = 0; i < COLS; ++i) {
        if (isValidMove(board, i)) validCols.push_back(i);
    }
    if (validCols.empty()) return -1;
    return validCols[rand() % validCols.size()];
}

int main() {
    srand(static_cast<unsigned>(time(0)));
    vector<vector<char>> board(ROWS, vector<char>(COLS, EMPTY));
    
    cout << "Welcome to Connect 4! You are 'X'. Computer is 'O'.\n";
    
    while (true) {
        printBoard(board);

        // Player move
        int playerCol;
        cout << "Enter your move (1-7): ";
        cin >> playerCol;
        playerCol--;

        if (!isValidMove(board, playerCol)) {
            cout << "Invalid move. Try again.\n";
            continue;
        }

        makeMove(board, playerCol, PLAYER);
        if (checkWin(board, PLAYER)) {
            printBoard(board);
            cout << "🎉 You win!\n";
            break;
        }
        if (isFull(board)) {
            printBoard(board);
            cout << "It's a tie!\n";
            break;
        }

        // Computer move
        int computerCol = getComputerMove(board);
        makeMove(board, computerCol, COMPUTER);
        cout << "Computer played column: " << (computerCol + 1) << "\n";
        if (checkWin(board, COMPUTER)) {
            printBoard(board);
            cout << "💻 Computer wins!\n";
            break;
        }
        if (isFull(board)) {
            printBoard(board);
            cout << "It's a tie!\n";
            break;
        }
    }

    return 0;
}
