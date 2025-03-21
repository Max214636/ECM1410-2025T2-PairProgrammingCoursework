import java.util.HashMap;
import java.util.Map;

public class GamesLeague implements GamesLeagueInterface {
    private Map<String, Map<String, Integer>> dailyScores; // Map<LeagueID, Map<PlayerEmail, Score>>
    
    public GamesLeague() {
        this.dailyScores = new HashMap<>();
    }

    /**
     * Registers a game report for a player in a given league.
     *
     * @param leagueID  The ID of the league where the game was played.
     * @param playerEmail The email of the player who played.
     * @param gameReport A string containing the player's dice rolls and final score.
     */
    @Override
    public void registerGameReport(String leagueID, String playerEmail, String gameReport) {
        System.out.println("Registering game report for " + playerEmail + " in League: " + leagueID);
        System.out.println("Game Report: " + gameReport);

        // Extract score from game report (assuming "score: X" format)
        int score = extractScore(gameReport);

        // Store the score in the league's daily record
        dailyScores.putIfAbsent(leagueID, new HashMap<>());
        dailyScores.get(leagueID).put(playerEmail, score);

        // Check if all players have submitted scores
        if (isRoundComplete(leagueID)) {
            registerDayScores(leagueID);
        }
    }

    /**
     * Extracts the score from a game report.
     *
     * @param gameReport The game report string.
     * @return The extracted score.
     */
    private int extractScore(String gameReport) {
        String[] parts = gameReport.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("score:")) {
                return Integer.parseInt(parts[i + 1]); // Score is right after "score:"
            }
        }
        return DiceRoll.getDefaultScore(); // If no score found, assign default (36)
    }

    /**
     * Checks if all players in a league have submitted their scores.
     *
     * @param leagueID The ID of the league.
     * @return true if all players have submitted scores, false otherwise.
     */
    private boolean isRoundComplete(String leagueID) {
        int playerCount = getLeaguePlayerCount(leagueID);
        return dailyScores.getOrDefault(leagueID, new HashMap<>()).size() == playerCount;
    }

    /**
     * Finalizes the round and updates league rankings.
     *
     * @param leagueID The ID of the league.
     */
    private void registerDayScores(String leagueID) {
        Map<String, Integer> scores = dailyScores.get(leagueID);
        System.out.println("Finalizing scores for League: " + leagueID);
        System.out.println(scores);

        // Logic to update league rankings
        // TODO: Implement ranking system and points allocation

        // Clear daily scores for the next round
        dailyScores.remove(leagueID);
    }

    /**
     * Retrieves the number of players in a league.
     *
     * @param leagueID The ID of the league.
     * @return The number of players.
     */
    private int getLeaguePlayerCount(String leagueID) {
        // TODO: Implement this based on stored league data
        return 4; // Temporary placeholder for testing
    }
}
