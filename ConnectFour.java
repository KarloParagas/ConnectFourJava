//Starter Code: Instructor
//Helper Methods: Me

import java.util.*;

public class ConnectFour {
    // board dimensions
    public static final int HEIGHT = 6; // number of rows in board
    public static final int WIDTH = 7; // number of columns in board
    
    // symbols appearing on the board
    public static final char RED = 'R';    // the only valid symbols
    public static final char YELLOW = 'Y'; // on the board are:
    public static final char BLANK = ' ';  // R, Y, and blank
    
    // ending game state is RED, YELLOW, or TIE
    public static final char TIE = 'T'; // tie game
    
    // Added by: Me
    // Array offset, used in finding 4 in a row, 4 in a column, 4 in an up and down diagonal
    public static final int offSet = 3;
    
    
    public static void main(String[] args) {
        char[][] board = new char[HEIGHT][WIDTH]; // current state of game
        initBoard(board); // replace default values with blanks
        
        Scanner console = new Scanner(System.in);
        
        boolean redPlayer = true; // keep track of current player
        
        while (!gameOver(board)) {
            displayBoard(board);
            if (redPlayer) {
                System.out.print("Drop a red disk at column (0-6):");
            }
            else {
                System.out.print("Drop a yellow disk at column (0-6):");
            }
            
            String userInput = console.nextLine();
            try {
                int column = Integer.parseInt(userInput);
                boolean legalPlay = dropToken(board, redPlayer, column); // update board
                if (legalPlay) {
                    redPlayer = !redPlayer; // switch player
                }
                else {
                    System.out.println("Column " + column + " is full.");
                }
            }
            catch (Exception e ) {
                System.out.println("Input value must be in range 0-6");
            }
        }
        
        char gameState = determineResults(board);
        if (gameState == RED) {
            System.out.println("Red wins!");
        }
        else if (gameState == YELLOW) {
            System.out.println("Yellow wins!");
        }
        else {
            System.out.println("Tie Game");
        }
    }
    
    // return RED, YELLOW, or TIE
    public static char determineResults(char[][] board) {
        if(fourInARow(board) == RED || fourInAColumn(board) == RED || fourInAUpDiagonal(board) == RED || fourInADownDiagonal(board) == RED) {
            return RED;
        }

        if(fourInARow(board) == YELLOW || fourInAColumn(board) == YELLOW || fourInAUpDiagonal(board) == YELLOW || fourInADownDiagonal(board) == YELLOW) {
            return YELLOW;
        }
        return TIE;
    }
    
    // when gameOver is true, we have a winner or a stalemate
    public static boolean gameOver(char[][] board) {
        if (fourInARow(board) == RED || fourInAColumn(board) == RED || fourInAUpDiagonal(board) == RED || fourInADownDiagonal(board) == RED
         || fourInARow(board) == YELLOW || fourInAColumn(board) == YELLOW || fourInAUpDiagonal(board) == YELLOW || fourInADownDiagonal(board) == YELLOW 
         || tie(board) == TIE) {
                return true;
        }
        return false;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //             Helper methods: Searches for 4 in a row, or 4 in column, or 4 in a diagonal.        //
    
    //Looks for 4 in a row
    public static char fourInARow(char[][] board) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length - offSet; col++) {
                if(board[row][col] == board[row][col + 1] 
                && board[row][col] == board[row][col + 2] 
                && board[row][col] == board[row][col + 3]) { 
                    if (board[row][col] == RED){
                        return RED;
                    }
                    if (board[row][col] == YELLOW) {
                        return YELLOW;
                    }
                    if(board[row][col] != BLANK) {
                        return TIE;
                    }
                }
            }
        }
        return BLANK;
    }
    
    //Looks for 4 in a column
    public static char fourInAColumn(char[][] board) {
        for(int row = 0; row < board.length - offSet; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(board[row][col] == board[row + 1][col]
                && board[row][col] == board[row + 2][col] 
                && board[row][col] == board[row + 3][col]) { 
                    if (board[row][col] == RED){
                        return RED;
                    }
                    if (board[row][col] == YELLOW) {
                        return YELLOW;
                    }
                    if(board[row][col] != BLANK) {
                        return TIE;
                    }
                }
            }
        } 
        return BLANK;
    }
    
    //Looks for 4 on a downward diagonal
    public static char fourInADownDiagonal(char[][] board) {
        for(int row = 0; row < board.length - offSet; row++){
            for(int col = 0; col < board[0].length - offSet; col++) {
                if(board[row][col] == board[row + 1][col + 1] 
                && board[row][col] == board[row + 2][col + 2] 
                && board[row][col] == board[row + 3][col + 3]) {
                    if (board[row][col] == RED) {
                        return RED;
                    }
                    if (board[row][col] == YELLOW) {
                        return YELLOW;
                    }
                    if(board[row][col] != BLANK) {
                        return TIE;
                    }
                }
            }
        }
        return BLANK;
    }
        
    //Looks for 4 on an upward diagonal
    public static char fourInAUpDiagonal(char[][] board) {
        for(int row = 0; row < board.length - offSet; row++) {
            for(int col = 0; col < board[0].length - offSet; col++) {
                if(board[row + 3][col] == board[row][col + 3]   
                && board[row + 3][col] == board[row + 1][col + 2]   
                && board[row + 3][col] == board[row + 2][col + 1]) { 
                    if (board[row + 3][col] == RED) {
                        return RED;
                    }
                    if (board[row + 3][col] == YELLOW) {
                        return YELLOW;
                    }
                    if(board[row + 3][col] != BLANK) {
                        return TIE;
                    }
                }
            }
        }        
        return BLANK;
    }
    
    //Looks for a tie
    public static char tie(char[][] board) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(board[row][col] == BLANK) {
                    return BLANK;
                }
            }
        }
        return TIE;
    }
   
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // returns true if token was placed, false if that column is full
    public static boolean dropToken(char[][] board, boolean redPlayer, int column) {
        // choose correct color
        char color = redPlayer ? RED : YELLOW; 

        // find correct row:
        // search for lowest blank spot
        int row = board.length - 1;
        while (row >= 0 && board[row][column] != BLANK) {
            --row;
        }
        if (row >= 0) { // we have a valid index
            board[row][column] = color;
            return true;
        }
        else { // index < 0 means column is full
            return false;
        }
    }
    
    public static void displayBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        for (int j = 0; j < board[board.length - 1].length; j++) {
            System.out.print("--");
        }
        System.out.println("-");
    }
    
    public static void initBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = BLANK;
            }
        }
    }
}