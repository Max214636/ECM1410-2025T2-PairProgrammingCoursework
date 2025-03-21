import java.util.*;

public class League {
    private String name;
    private String gameType;
    private Player owner;
    private List<Player> players;
    private Map<Player, Integer> dailyScores;

    public League(String name, String gameType, Player owner) {
        this.name = name;
        this.gameType = gameType;
        this.owner = owner;
        this.players = new ArrayList<>();
        this.dailyScores = new HashMap<>();
    }

    public void recordGameResult(Player player, int score) {
        dailyScores.put(player, score);
    }

    public List<String> getDayScores() {
        List<String> scores = new ArrayList<>();
        for (Map.Entry<Player, Integer> entry : dailyScores.entrySet()) {
            scores.add(entry.getKey().getDisplayName() + ": " + entry.getValue());
        }
        return scores;
    }
}
