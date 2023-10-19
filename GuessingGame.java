import java.util.*;

// Uyen Tran
// 11/04/2020
// CSE 142 AU20
// TA: Rinav Kasthuri
// Assessment 5: Guessing game

// This program will let users play a game of guessing 
// a random number and print out overall statistics of the game.

public class GuessingGame {
    public static final int MAX_VALUE = 100;
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random r = new Random();

        int numPlay = 1;

        haiku();
        int numGuess = oneGame(console, r);
        int best = numGuess;
        int total = numGuess;
        char first = decide(console);

        while (first == 'y') { // if the first letter of the answer is "y" or "Y" then keep playing
            numGuess = oneGame(console, r);
            first = decide(console);
            numPlay++; // Adds up the number of games played
            total += numGuess; // Adds up the number of guesses

            if (numGuess < best) {
                best = numGuess;
            }
        }

        getStats(numPlay, total, best);
    }

// Print the haiku poem
    public static void haiku() {
        System.out.println("As the moon rises to the top");
        System.out.println("Light disappears,");
        System.out.println("And numbers show in darkness.");
    }

// Play one game
// return the number of guesses
// Parameters:
//       Scanner console: the terminal that takes guesses from the user
//       Random r: a random-numbers generator
    public static int oneGame(Scanner console, Random r) {
        int numGuess = 1;
        int random = r.nextInt(MAX_VALUE) + 1; // generates a number from 1 to the given limit

        System.out.println("I'm thinking of a number between 1 and " + MAX_VALUE + "...");
        System.out.print("Your guess? ");
        int answer = console.nextInt();

        while (answer != random) { // if the users get the wrong answer then keep asking for guesses
            if (answer > random) {
                System.out.println("It's lower.");
            } else {
                System.out.println("It's higher.");
            }
            System.out.print("Your guess? ");
            answer = console.nextInt();
            numGuess++; // Adds up the number of guesses
        }

        if (numGuess > 1) {
            System.out.println("You got it right in " + numGuess + " guesses!");
        } else {
            System.out.println("You got it right in 1 guess!");
        }

        return numGuess;
    }

// Ask the user whether one wants to keep playing or not
// return the first letter of the answer
// Parameters:
//       Scanner console: the terminal that takes guesses from the user
    public static char decide(Scanner console) {
        System.out.print("Do you want to play again? ");
        String choose = console.next();
        char first = choose.toLowerCase().charAt(0); // get the first letter of the answer
        System.out.println();

        return first;
    }

// Print out statistics of the game(s)
// Parameters:
//       int numPlay: the number of games played
//       int total: the number of total guesses
//       int best: the smallest number of guesses
    public static void getStats(int numPlay, int total, int best) {
        double ratio = (double)total / numPlay;
        System.out.println("Overall results:");
        System.out.println("Total games   = " + numPlay);
        System.out.println("Total guesses = " + total);
        System.out.println("Guesses/game  = " + round1(ratio));
        System.out.println("Best game     = " + best);
    }

// Round a number to 1 decimal place
// return the rounded number
// Parameters:
//       double num: the number that needs rounding
    public static double round1(double num) {
        return Math.round(num * 10.0) / 10.0;
    }
    
}
