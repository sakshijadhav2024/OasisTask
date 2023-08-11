import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min= 1;
        int max = 100;
        int highestChance = 5;
        int rounds = 0;
        int totalChance= 0;

        boolean playAgain = true;

        System.out.println("Welcome to Number Game!");

        while (playAgain) {
            rounds++;
            int secretNumber = random.nextInt(max - min + 1) + min;
            System.out.println("\nRound " + rounds + ". You have " + highestChance + " attempts.");

            int attempts = 0;
            boolean correctGuess = false;

            while (attempts < highestChance) {
                attempts++;
                System.out.print("guess the number: ");

                int userGuess;
                try {
                    userGuess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                }

                if (userGuess < secretNumber) {
                    System.out.println("Too low");
                } else if (userGuess > secretNumber) {
                    System.out.println("Too high");
                } else {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                    correctGuess = true;
                    break;
                }
            }

            if (!correctGuess) {
                System.out.println("attempts are over.... The correct number was: " + secretNumber);
            }

            totalChance += attempts;

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.nextLine().toLowerCase();
            playAgain = playAgainInput.equals("yes") || playAgainInput.equals("y");
        }

        System.out.println("Thanks for playing! Your total score is based on the total attempts: " + totalChance);
        scanner.close();
    }
}
