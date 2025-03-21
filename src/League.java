package gamesleague;

import java.io.Serializable;
import java.util.*;

public class League implements Serializable {
    private String leagueName;
    private String gameType;
    private List<Player> players;
    private Map<String, Integer> scores;

    public League(String leagueName, String gameType) {
        this.leagueName = leagueName;
        this.gameType = gameType;
        this.players = new ArrayList<>();
        this.scores = new HashMap<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
        scores.put(player.getEmail(), 0);
    }

    public void recordScore(String email, int score) {
        if (scores.containsKey(email)) {
            scores.put(email, scores.get(email) + score);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public String getLeagueName() {
        return leagueName;
    }

    @Override
    public String toString() {
        return "League: " + leagueName + " (" + gameType + ")";
    }
}

        return scores;
    }
}
