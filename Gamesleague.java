import java.util.*;

public class GamesLeague implements GamesLeagueInterface {
    private Map<String, Map<String, Integer>> dailyScores; // LeagueID -> (PlayerEmail -> Score)
    private Map<String, Map<String, Integer>> leaguePoints; // LeagueID -> (PlayerEmail -> TotalPoints)

    public GamesLeague() {
        this.dailyScores = new HashMap<>();
        this.leaguePoints = new HashMap<>();
    }

    @Override
    public void registerGameReport(String leagueID, String playerEmail, String gameReport) {
        System.out.println("Registering game report for " + playerEmail + " in League: " + leagueID);
        System.out.println("Game Report: " + gameReport);

        // Extract score from game report
        int score = extractScore(gameReport);

        // Store the score in the league's daily record
        dailyScores.putIfAbsent(leagueID, new HashMap<>());
        dailyScores.get(leagueID).put(playerEmail, score);

        // Check if all players have submitted scores
        if (isRoundComplete(leagueID)) {
            registerDayScores(leagueID);
        }
    }

    private int extractScore(String gameReport) {
        String[] parts = gameReport.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("score:")) {
                return Integer.parseInt(parts[i + 1]);
            }
        }
        return 36; // Default score for absent players
    }

    private boolean isRoundComplete(String leagueID) {
        int playerCount = getLeaguePlayerCount(leagueID);
        return dailyScores.getOrDefault(leagueID, new HashMap<>()).size() == playerCount;
    }

    private void registerDayScores(String leagueID) {
        Map<String, Integer> scores = dailyScores.get(leagueID);
        System.out.println("Finalizing scores for League: " + leagueID);
        System.out.println("Daily Scores: " + scores);

        // Sort players by scores (ascending order for DICEROLL)
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort(Map.Entry.comparingByValue());

        // Award league points
        Map<String, Integer> points = leaguePoints.computeIfAbsent(leagueID, k -> new HashMap<>());
        int previousScore = -1;
        int currentPoints = 3; // 3 points for lowest score
        int rank = 1;

        for (Map.Entry<String, Integer> entry : sortedScores) {
            String player = entry.getKey();
            int score = entry.getValue();

            // If score changes, adjust points
            if (score != previousScore) {
                if (rank == 2) currentPoints = 1; // 1 point for second place
                else currentPoints = 0; // 0 for the rest
            }

            points.put(player, points.getOrDefault(player, 0) + currentPoints);
            previousScore = score;
            rank++;
        }

        System.out.println("Updated League Points: " + points);

        // Clear daily scores for the next round
        dailyScores.remove(leagueID);
    }

    private int getLeaguePlayerCount(String leagueID) {
        return 4; // Placeholder for testing
    }
}

}
