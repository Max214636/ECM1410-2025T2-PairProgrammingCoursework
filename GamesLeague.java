import java.util.*;

public class GamesLeague implements GamesLeagueInterface {
    private Map<String, Player> players;
    private Map<String, League> leagues;

    public GamesLeague() {
        this.players = new HashMap<>();
        this.leagues = new HashMap<>();
    }

    @Override
    public void registerPlayer(String email, String displayName, String realName) {
        if (!players.containsKey(email)) {
            players.put(email, new Player(email, displayName, realName));
        }
    }

    @Override
    public boolean playerExists(String email) {
        return players.containsKey(email);
    }

    @Override
    public void createLeague(String leagueName, String gameType, String ownerEmail) {
        if (!leagues.containsKey(leagueName) && players.containsKey(ownerEmail)) {
            leagues.put(leagueName, new League(leagueName, gameType, players.get(ownerEmail)));
        }
    }

    @Override
    public void registerGameReport(String leagueName, String email, int score) {
        if (leagues.containsKey(leagueName) && players.containsKey(email)) {
            leagues.get(leagueName).recordGameResult(players.get(email), score);
        }
    }

    @Override
    public List<String> getDayScores(String leagueName) {
        if (leagues.containsKey(leagueName)) {
            return leagues.get(leagueName).getDayScores();
        }
        return new ArrayList<>();
    }
}
