package gamesleague;

import java.io.Serializable;
import java.util.*;

/**
 * the League class manages the details of a league including players and scores
 * it provides methods for adding players recording scores and retrieving league data
 */
public class League implements Serializable { // making the class serializable for saving and loading
    private String leagueName // the name of the league
    private String gameType // the type of game associated with the league
    private List<Player> players // list to store all players in the league
    private Map<String, Integer> scores // map to store player scores indexed by email

    /**
     * constructor initializes a league with a name a game type and an owner
     * @param leagueName name of the league
     * @param gameType type of game associated with the league
     * @param owner the owner of the league must be a player
     */
    public League(String leagueName, String gameType, Player owner) {
        this.leagueName = leagueName
        this.gameType = gameType
        this.players = new ArrayList<>()
        this.scores = new HashMap<>()
        addPlayer(owner) // adds the owner to the league
    }

    /**
     * adds a player to the league and initializes their score to 0
     * ensures no duplicate players are added
     * @param player the player to be added
     */
    public void addPlayer(Player player) {
        if (!players.contains(player)) { // checks if the player is already in the league
            players.add(player)
            scores.put(player.getEmail(), 0) // initializes the player's score
            System.out.println("player added to league: " + player.getDisplayName())
        } else {
            System.out.println("error: player is already part of the league")
        }
    }

    /**
     * records the result of a game for a player in the league
     * @param player the player whose score is to be recorded
     * @param score the score achieved by the player
     */
    public void recordGameResult(Player player, int score) {
        if (scores.containsKey(player.getEmail())) { // checks if the player exists in the league
            scores.put(player.getEmail(), scores.get(player.getEmail()) + score) // updates the player's score
            System.out.println("game result recorded for " + player.getEmail())
        } else {
            System.out.println("error: player not found in the league")
        }
    }

    /**
     * retrieves a list of scores for the current round
     * @return list of scores formatted as strings
     */
    public List<String> getDayScores() {
        List<String> dayScores = new ArrayList<>()
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            dayScores.add(entry.getKey() + ": " + entry.getValue()) // formats scores as email: score
        }
        return dayScores
    }

    /**
     * retrieves the name of the league
     * @return the name of the league
     */
    public String getLeagueName() {
        return leagueName
    }

    /**
     * retrieves the type of game associated with the league
     * @return the type of game
     */
    public String getGameType() {
        return gameType
    }

    /**
     * overrides the toString method to provide a string representation of the league
     * @return league details including name and game type
     */
    @Override
    public String toString() {
        return "league name: " + leagueName + " game type: " + gameType
    }
}
