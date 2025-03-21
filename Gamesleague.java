package gamesleague;

import java.io.*;
import java.util.*;

public class GamesLeague {
    private List<Player> players;
    private List<League> leagues;

    public GamesLeague() {
        players = new ArrayList<>();
        leagues = new ArrayList<>();
    }

    public void createPlayer(String displayName, String email) {
        players.add(new Player(displayName, email));
    }

    public Player getPlayerByEmail(String email) {
        for (Player p : players) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

    public void createLeague(String leagueName, String gameType) {
        leagues.add(new League(leagueName, gameType));
    }

    public League getLeagueByName(String leagueName) {
        for (League l : leagues) {
            if (l.getLeagueName().equals(leagueName)) {
                return l;
            }
        }
        return null;
    }

    public void saveData(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GamesLeague loadData(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (GamesLeague) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new GamesLeague();
        }
    }

    public static void main(String[] args) {
        GamesLeague gl = new GamesLeague();
        gl.createPlayer("Alice", "alice@example.com");
        gl.createLeague("DiceRoll League", "DICEROLL");

        League league = gl.getLeagueByName("DiceRoll League");
        Player alice = gl.getPlayerByEmail("alice@example.com");

        if (league != null && alice != null) {
            league.addPlayer(alice);
            league.recordScore(alice.getEmail(), 5);
        }

        gl.saveData("gamesleague.dat");
    }
}

}
