import java.util.Arrays
import java.util.Random

// the DiceRoll class simulates a game where players roll dice to achieve the lowest score
// includes methods for rolling dice removing sequences and calculating scores
public class DiceRoll {
    private static final int NUM_DICE = 6 // number of dice used in the game
    private static final int DEFAULT_SCORE = 36 // default score assigned if a player does not play

    private int[] dice // array to store dice values
    private Random random // random generator for dice rolls

    // constructor initializes dice and rolls all dice once
    public DiceRoll() {
        this.dice = new int[NUM_DICE]
        this.random = new Random()
        rollAllDice()
    }

    /**
     * rolls all dice to assign initial random values between 1 and 6
     */
    private void rollAllDice() {
        for (int i = 0; i < NUM_DICE; i++) {
            dice[i] = random.nextInt(6) + 1 // generates random dice values between 1 and 6
        }
    }

    /**
     * rerolls dice based on player choices specified in the keep array
     * @param keep array indicating which dice to keep true means keep false means reroll
     */
    public void rerollDice(boolean[] keep) {
        for (int i = 0; i < NUM_DICE; i++) {
            if (!keep[i]) { // rerolls dice marked as false
                dice[i] = random.nextInt(6) + 1
            }
        }
    }

    /**
     * removes consecutive sequences of 3 or more numbers from the dice
     * updates the dice array to keep only the remaining values
     */
    private void removeSequences() {
        Arrays.sort(dice) // sorts dice to identify sequences more easily

        boolean[] toRemove = new boolean[NUM_DICE] // array to mark dice for removal
        int count = 1 // counts consecutive numbers

        for (int i = 1; i < NUM_DICE; i++) {
            if (dice[i] == dice[i - 1] + 1) { // checks if current die forms part of a sequence
                count++
                if (count >= 3) { // marks the dice for removal if sequence length is 3 or more
                    toRemove[i] = true
                    toRemove[i - 1] = true
                    toRemove[i - 2] = true
                }
            } else if (dice[i] != dice[i - 1]) { // resets count if sequence breaks
                count = 1
            }
        }

        // updates the dice array to keep only non-removed numbers
        int[] newDice = new int[NUM_DICE]
        int index = 0
        for (int i = 0; i < NUM_DICE; i++) {
            if (!toRemove[i]) {
                newDice[index++] = dice[i]
            }
        }
        dice = Arrays.copyOf(newDice, index) // resizes the dice array to keep valid values only
    }

    /**
     * calculates the final score by summing the remaining dice values
     * @return total score based on remaining dice
     */
    public int calculateScore() {
        removeSequences() // removes consecutive sequences first
        int totalScore = 0
        for (int die : dice) {
            totalScore += die // sums remaining dice values
        }
        return totalScore
    }

    /**
     * simulates a full round for a player with predefined dice-keeping patterns
     * @return the final score after three rolls
     */
    public int play() {
        rerollDice(new boolean[]{true, false, true, false, true, false}) // first reroll
        rerollDice(new boolean[]{true, true, false, true, true, false}) // second reroll
        return calculateScore() // calculates the final score
    }

    /**
     * retrieves the default score assigned to players who do not participate
     * @return default score value
     */
    public static int getDefaultScore() {
        return DEFAULT_SCORE
    }

    /**
     * prints the current dice values for debugging purposes
     */
    public void printDice() {
        System.out.println("current dice: " + Arrays.toString(dice))
    }
}
