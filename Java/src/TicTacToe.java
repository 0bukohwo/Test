import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe
{
    static String[] board = new String[9];
    static Scanner reader = new Scanner(System.in);
    static String player1, player2, winner, player, playAgain, samePlayers;
    static int choice;
    static boolean exit, getNames;

    static void printBoard()
    {
        for (int i =0; i<=6; i=i+3)
        {
            System.out.println("|-----------|");
            System.out.println(("| "+board[i]+" | "+board[i+1]+" | "+board[i+2]+" |"));
        }
        System.out.println("|-----------|");
    }

    static void reset()
    {
        winner = null;
        choice = 0;
        player = "X";
    }

    static String checkWinner()
    {
        for (int i=0; i<8; i++)
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

        for (int i = 0; i<9; i++)
        {
            if (Arrays.asList(board).contains(String.valueOf(i+1)))
            {
                break;
            }
            else if (i==8) return "draw";
        }

        if(player.equals("X"))
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

    static void actualGame()
    {
        System.out.println("\nHere's your starting board");

        for (int i=1; i<=9; i++ )
        {
            board[i-1] = Integer.toString(i);
        }
        printBoard();

        System.out.println("As is the rule of TicTacToe, "+player1+" will go first.");
        System.out.println("Please choose a cell number for the first X:");

        while (winner == null)
        {
            try
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

            if (board[choice-1].equals(String.valueOf(choice)))
            {
                board[choice-1] = player;
                if (player.equals("X"))
                {
                    player = "O";
                }
                else player = "X";

                printBoard();
                winner = checkWinner();
            }
            else
            {
                System.out.println("The cell you chose is already occupied.\nPlease choose another cell");
            }
        }

        switch(winner)
        {
            case "draw":
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

    static void getNames()
    {
        System.out.println("You'll need 2 players for the game");
        System.out.println("Player 1 (X), what's your name?");
        player1 = reader.nextLine();
        System.out.println("Player 2 (O), what's your name?");
        player2 = reader.nextLine();

        System.out.println("\nNow I know your names");
    }

    static void restart()
    {
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
                getNames = false;
            }
        }

        if (playAgain.equalsIgnoreCase("n"))
        {
            exit = true;
        }

        if(playAgain.equalsIgnoreCase("y"))
        {
            exit = false;
            reset();
        }
    }

    public static void main (String[] args)
    {
        reset();
        exit = false;
        getNames = true;

        System.out.println("Welcome to Otome's TicTacToe.");

        while (exit == false)
        {
            if(getNames == true)
            {
                getNames();
            }

            actualGame();

            restart();
        }

        System.out.println("Bye bye");
    }
}
