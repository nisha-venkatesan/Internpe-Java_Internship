import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        // Create a random number between 1 and 100
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;

        Scanner scanner = new Scanner(System.in);
        int numberOfTries = 0;
        int userGuess;
        boolean hasGuessedCorrectly = false;

        System.out.println("🎮 Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100:");

        // Game loop
        while (!hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();
            numberOfTries++;

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("🎉 Congratulations! You guessed the number in " + numberOfTries + " tries.");
            }
        }

        scanner.close();
    }
}
