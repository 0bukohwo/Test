import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe
{
    static Scanner reader = new Scanner(System.in);
    static String player1; // The name of the player playing X's
    static String player2; // The name of the player playing O's
    static String winner; // The player who wins the game (X or O)
    static String player; // The player whose turn it is
    static String playAgain; // The decision to play again
    static String samePlayers; // The decision to play again with the same players
    static String[] board = new String[9]; // The TIcTacToe board
    static int choice; // The choice cell number on the board
    static boolean exit; // The decision to exit the game
    static boolean  getNames;// The decision to take names of players

    static void printBoard() // Print the most recent board to the screen
    {
        for (int i =0; i<=6; i=i+3)
        {
            System.out.println("|-----------|");
            System.out.println(("| "+board[i]+" | "+board[i+1]+" | "+board[i+2]+" |"));
        }
        System.out.println("|-----------|");
    }

    static void reset() // Reset the necessary variable before starting a new game
    {
        winner = null;
        choice = 0;
        player = "X";
    }

    static String checkWinner() // Decide if the game is over and determine the winner if there is one
    {
        for (int i=0; i<8; i++) // Check all 7 possible cases for a win and determine the winner, if any
        {
            String line = null;

            switch (i)
            {
                case 0:
                    line = board[0]+board[1]+board[2];
                    break;
                case 1:
                    line = board[3]+board[4]+board[5];
                    break;
                case 2:
                    line = board[6]+board[7]+board[8];
                    break;
                case 3:
                    line = board[0]+board[4]+board[8];
                    break;
                case 4:
                    line = board[2]+board[4]+board[6];
                    break;
                case 5:
                    line = board[1]+board[4]+board[7];
                    break;
                case 6:
                    line = board[0]+board[3]+board[6];
                    break;
                case 7:
                    line = board[2]+board[5]+board[8];
                    break;
            }

            if (line.equals("XXX"))
            {
                return "X";
            }
            else if (line.equals("OOO"))
            {
                return "O";
            }
        }

        for (int i = 0; i<9; i++) // Check if there are still spaces left on the board. If there are not, call the game a tie
        {
            if (Arrays.asList(board).contains(String.valueOf(i+1)))
            {
                break;
            }
            else if (i==8) return "tie";
        }

        if(player.equals("X")) // Call the player with the next turn
        {
            System.out.println("It's "+player1+"'s turn to play");
            System.out.println("Please pick cell number to place an X in:");
        }
        else
        {
            System.out.println("It's "+player2+"'s turn to play");
            System.out.println("Please pick cell number to place an O in:");
        }

        return null;
    }

    static void actualGame() // Main game method
    {
        System.out.println("\nHere's your starting board");

        for (int i=1; i<=9; i++ ) // Fills the board up with numbers at the beginning of each game
        {
            board[i-1] = Integer.toString(i);
        }

        printBoard();

        System.out.println("As is the rule of TicTacToe, "+player1+" will go first.");
        System.out.println("Please choose a cell number for the first X:");

        while (winner == null) // Loop to continue game until a winner is found
        {
            try // try-catch to check user input before proceeding
            {
                choice = reader.nextInt();

                if(choice < 1 || choice > 9)
                {
                    System.out.println("Please enter a cell number from 1 to 9");
                    continue;
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter an integer from 1 to 9");
                continue;
            }

            if (board[choice-1].equals(String.valueOf(choice))) // Check to make sure the chosen cell is empty
            {
                board[choice-1] = player; // Puts an X or O in the chosen cell

                printBoard();

                if (player.equals("X")) // Switch the player for the next turn
                {
                    player = "O";
                }
                else player = "X";

                winner = checkWinner();
            }
            else
            {
                System.out.println("The cell you chose is already occupied.\nPlease choose another cell:");
            }
        }

        switch(winner) // Check's to see if there is a winner and returns the appropriate message
        {
            case "tie":
            {
                System.out.println("It's a tie!");
                break;
            }
            case "X":
            {
                System.out.println(player1+" takes this game. Better luck next time "+player2);
                break;
            }
            case "O":
            {
                System.out.println(player2+" takes this game. Better luck next time "+player1);
                break;
            }
        }
    }

    static void getNames() // Collects the names of the players
    {
        System.out.println("You'll need 2 players for the game");

        System.out.println("Player 1 (X), what's your name?");
        player1 = reader.nextLine();

        System.out.println("Player 2 (O), what's your name?");
        player2 = reader.nextLine();

        System.out.println("\nNow I know your names");
    }

    static void restart() // Checks if the players want to restart the game with the same or different players
    {
        String placeholder = "placeholder";
        Boolean error = true;

        System.out.println("\nWant to play again? Enter \"Y\" for yes and \"N\" for no");
        reader.nextLine();

        try
        {
            playAgain = reader.nextLine();

            if(!(playAgain.equalsIgnoreCase("y") || playAgain.equalsIgnoreCase("n")))
            {
                System.out.println("Please enter \"y\" or \"n\"");
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("Please enter \"y\" or \"n\"");
        }

        if (playAgain.equalsIgnoreCase("y"))
        {
            System.out.println("\nSame players? Enter \"Y\" for yes and \"N\" for no");

            try
            {
                samePlayers = reader.nextLine();

                if(!(samePlayers.equalsIgnoreCase("y") || samePlayers.equalsIgnoreCase("n")))
                {
                    System.out.println("Please enter \"y\" or \"n\"");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter \"y\" or \"n\"");
            }

            if (samePlayers.equalsIgnoreCase("y"))
            {
                getNames = false; // Skips the getNames method if the same players are playing again


                System.out.println("\nWho wants to go first this time?\n"+player1+" or "+player2+" ?"); // Checks to see if players want to swap order of play

                try
                {
                    placeholder = reader.nextLine();

                    if(!(placeholder.equalsIgnoreCase(player1) || placeholder.equalsIgnoreCase(player2)))
                    {
                        System.out.println(" I did not get that. Please choose one of the names listed.");
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Please choose one of the names listed.");
                }

                if (placeholder.equalsIgnoreCase(player2))
                {
                    placeholder = player1;
                    player1 = player2;
                    player2 = placeholder;
                }
            }

            exit = false;
            reset();
        }

        if (playAgain.equalsIgnoreCase("n")) // Exits the game if players do not want to play again
        {
            exit = true;
        }
    }

    public static void main (String[] args) // Main program
    {
        reset();
        exit = false;
        getNames = true;

        System.out.println("Welcome to Otome's TicTacToe."); // Introductory message, only appears at the beginning of the program

        while (!exit)
        {
            if(getNames)
            {
                getNames();
            }

            actualGame();

            restart();
        }

        System.out.println("Bye bye");
    }
}
