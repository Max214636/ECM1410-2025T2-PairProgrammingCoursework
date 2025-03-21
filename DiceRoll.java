import java.util.Arrays;
import java.util.Random;

public class DiceRoll {
    private static final int NUM_DICE = 6;
    private static final int DEFAULT_SCORE = 36; // Default score if player doesn't play

    private int[] dice;
    private Random random;

    public DiceRoll() {
        this.dice = new int[NUM_DICE];
        this.random = new Random();
        rollAllDice();
    }

    // Roll all dice
    private void rollAllDice() {
        for (int i = 0; i < NUM_DICE; i++) {
            dice[i] = random.nextInt(6) + 1; // Rolls between 1 and 6
        }
    }

    // Reroll selected dice (simulating player choice)
    public void rerollDice(boolean[] keep) {
        for (int i = 0; i < NUM_DICE; i++) {
            if (!keep[i]) {
                dice[i] = random.nextInt(6) + 1;
            }
        }
    }

    // Remove consecutive sequences of 3+ numbers
    private void removeSequences() {
        Arrays.sort(dice); // Sort dice to identify sequences

        boolean[] toRemove = new boolean[NUM_DICE];
        int count = 1;

        for (int i = 1; i < NUM_DICE; i++) {
            if (dice[i] == dice[i - 1] + 1) {
                count++;
                if (count >= 3) {
                    toRemove[i] = true;
                    toRemove[i - 1] = true;
                    toRemove[i - 2] = true;
                }
            } else if (dice[i] != dice[i - 1]) {
                count = 1; // Reset count if sequence breaks
            }
        }

        // Remove marked dice
        int sum = 0;
        for (int i = 0; i < NUM_DICE; i++) {
            if (!toRemove[i]) {
                sum += dice[i];
            }
        }

        // Update dice array (only keep remaining numbers)
        int[] newDice = new int[NUM_DICE];
        int index = 0;
        for (int i = 0; i < NUM_DICE; i++) {
            if (!toRemove[i]) {
                newDice[index++] = dice[i];
            }
        }
        dice = Arrays.copyOf(newDice, index); // Resize array to keep only remaining dice
    }

    // Calculate the final score
    public int calculateScore() {
        removeSequences();
        int totalScore = 0;
        for (int die : dice) {
            totalScore += die;
        }
        return totalScore;
    }

    // Simulate a full round for a player
    public int play() {
        // Simulate three rolls (player keeps some dice)
        rerollDice(new boolean[]{true, false, true, false, true, false});
        rerollDice(new boolean[]{true, true, false, true, true, false});

        return calculateScore();
    }

    // Get default score for absent players
    public static int getDefaultScore() {
        return DEFAULT_SCORE;
    }

    // Display dice for debugging
    public void printDice() {
        System.out.println("Current Dice: " + Arrays.toString(dice));
    }
}
