
/**
 * Write a description of class main here.
 *
 * @author Parsa S.
 * @version Problem Set 8A / 10/11/2023
 */

import java.util.Scanner;

public class main
{
    public static void main() { // Game Setup
        char[][] game = new char[3][3];
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) game[i][j] = '_';
        String s = "";
        Scanner input = new Scanner(System.in);
        int round = 1;
        int row = -1, column = -1;
        boolean player = true; // True for X, False for O
        boolean gameOn = true;

        /*  Gameplan:
         *  Big Boi Game Loop
         *  Keep asking until space is not taken
         *  Submit space
         *  Check if game is won
         *  Switch players and repeat loop
         * 
         *  Also need to keep track of player (boolean), round (int), and what else?
         *  
         *  Basically, layer "Return to Death" three times:
         *  1. Answer loop. Redo if answer is not valid or space is already taken
         *  2. Round loop. Once answer is submitted, redo loop
         *  3. Game loop. Redo once game is over.
         *  
         */

        while (gameOn) { // Game Loop
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) game[i][j] = '_';    
            round = 1;
            player = true;
            System.out.println("Welcome to the classic game, \"Tik Tak Two\"!");

            while (true) { // Rounds loop
                System.out.println();
                System.out.println("Round " + round);
                showBoard(game);

                System.out.print(playerXO(player));
                System.out.println(", make your move (row, column):");

                s = input.nextLine(); // Recieve response
                s = s.trim();

                row = Integer.parseInt(s.substring(0, 1));
                column = Integer.parseInt(s.substring(2, 3));
                if (row < 0 || row > 2 || column < 0 || column > 2) {
                    System.out.println("That space is not on the board, try again.");
                    continue;
                } else if (spaceIsEmpty(row, column, game)) { // Add mark into game
                    game[row][column] = playerXO(player);
                } else {
                    System.out.println("That space is already occupied, try again.");
                    continue;
                }

                if (hasWon(playerXO(player), game)) {
                    System.out.println();
                    System.out.println(playerXO(player) + " has won!");
                    System.out.println("Another round? Y/N ");
                    s = input.nextLine(); 
                    s = s.trim();

                    if (s.equals("Y")) {
                        break;
                    } else if (s.equals("N")) {
                        gameOn = false;
                        break;
                    } 

                } else if (round == 9) {
                    System.out.println("It's a Tie!");
                    
                    System.out.println("Another round? Y/N "); // Repeated code with no method? Yes.
                    s = input.nextLine(); 
                    s = s.trim();

                    if (s.equals("Y")) {
                        break;
                    } else if (s.equals("N")) {
                        gameOn = false;
                        break;
                    } 

                }

                player = !player;
                round++;
                                    

            }
            
            
            
        }
    }

    public static boolean hasWon(char p, char[][] game) {
        for (int i = 0; i < 3; i++) { // Row and Column Checker 
            if (game[i][0] == p && (game[i][0] == game[i][1] && game[i][0] == game[i][2])) return true; // Row
            if (game[0][i] == p && (game[0][i] == game[1][i] && game[0][i] == game[2][i])) return true; // Column
        }

        // Diagonal Checker
        if (game[0][0] == p && (game[0][0] == game[1][1] && game[0][0] == game[2][2])) return true; 
        if (game[0][2] == p && (game[0][2] == game[1][1] && game[0][2] == game[2][0])) return true;

        return false; // If no matches are found, the player has not won
    }

    public static void showBoard(char a[][]) { // Show the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("{" + a[i][j] + "} ");
            }
            System.out.println("");
        }
    }

    public static boolean spaceIsEmpty(int x, int y, char[][] game) {
        if (game[x][y] == '_') return true;
        return false;
    }

    public static char playerXO (boolean b) {
        if (b) return 'X';
        else return 'O';
    }
}
