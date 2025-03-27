import java.util.*;

// the GamesLeague class implements the GamesLeagueInterface and provides the backend functionality for managing players and leagues
public class GamesLeague implements GamesLeagueInterface {
    private Map<String, Player> players // map to store players indexed by email
    private Map<String, League> leagues // map to store leagues indexed by league name

    // constructor initializes the player and league maps
    public GamesLeague() {
        this.players = new HashMap<>()
        this.leagues = new HashMap<>()
    }

    /**
     * registers a new player in the system if the email is not already in use
     * @param email the player's email must be unique
     * @param displayName the player's display name
     * @param realName the player's real name
     */
    @Override
    public void registerPlayer(String email, String displayName, String realName) {
        if (!players.containsKey(email)) { // checks if the email already exists
            players.put(email, new Player(email, displayName, realName)) // adds the new player to the map
            System.out.println("player registered successfully: " + displayName)
        } else {
            System.out.println("error: player with this email already exists")
        }
    }

    /**
     * checks if a player exists in the system using their email
     * @param email the player's email
     * @return true if the player exists false otherwise
     */
    @Override
    public boolean playerExists(String email) {
        return players.containsKey(email) // checks the map for the email
    }

    /**
     * creates a new league in the system if the name is unique and the owner exists
     * @param leagueName the name of the league
     * @param gameType the type of game associated with the league
     * @param ownerEmail the email of the player who owns the league
     */
    @Override
    public void createLeague(String leagueName, String gameType, String ownerEmail) {
        if (!leagues.containsKey(leagueName) && players.containsKey(ownerEmail)) { // checks if the league name is unique and the owner exists
            leagues.put(leagueName, new League(leagueName, gameType, players.get(ownerEmail))) // creates and adds the league
            System.out.println("league created successfully: " + leagueName)
        } else {
            System.out.println("error: league already exists or owner email is invalid")
        }
    }

    /**
     * registers a game report for a player in a specific league
     * @param leagueName the name of the league
     * @param email the player's email
     * @param score the score achieved by the player
     */
    @Override
    public void registerGameReport(String leagueName, String email, int score) {
        if (leagues.containsKey(leagueName) && players.containsKey(email)) { // checks if both the league and player exist
            leagues.get(leagueName).recordGameResult(players.get(email), score) // records the game result
            System.out.println("game report registered for player: " + email + " in league: " + leagueName)
        } else {
            System.out.println("error: invalid league name or player email")
        }
    }

    /**
     * retrieves the day scores for a specific league
     * @param leagueName the name of the league
     * @return a list of strings containing player scores for the day
     */
    @Override
    public List<String> getDayScores(String leagueName) {
        if (leagues.containsKey(leagueName)) { // checks if the league exists
            return leagues.get(leagueName).getDayScores() // retrieves the day scores from the league
        }
        System.out.println("error: league not found")
        return new ArrayList<>() // returns an empty list if the league does not exist
    }
}
