package gamesleague;

import java.io.Serializable;

public class Player implements Serializable {
    private String displayName;
    private String email;
    private boolean isActive;

    public Player(String displayName, String email) {
        this.displayName = displayName;
        this.email = email;
        this.isActive = true;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean status) {
        this.isActive = status;
    }

    @Override
    public String toString() {
        return displayName + " (" + email + ") - " + (isActive ? "Active" : "Inactive");
    }
}
