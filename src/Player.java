package gamesleague;

import java.io.Serializable;

/**
 * the Player class represents a player in the system with their details
 * includes attributes like email display name real name and active status
 */
public class Player implements Serializable { // making the class serializable for persistence
    private String email // the unique email of the player
    private String displayName // the player's display name
    private String realName // the player's real name
    private boolean isActive // indicates whether the player is active

    /**
     * constructor initializes a player with their email display name and real name
     * sets the active status to true by default
     * @param email the player's email must be unique
     * @param displayName the player's display name
     * @param realName the player's real name
     */
    public Player(String email, String displayName, String realName) {
        if (email == null || email.isEmpty() || displayName == null || displayName.isEmpty() || realName == null || realName.isEmpty()) {
            throw new IllegalArgumentException("email display name and real name cannot be null or empty")
        }
        this.email = email
        this.displayName = displayName
        this.realName = realName
        this.isActive = true // sets the default status to active
    }

    /**
     * retrieves the email of the player
     * @return the player's email
     */
    public String getEmail() {
        return email
    }

    /**
     * retrieves the display name of the player
     * @return the player's display name
     */
    public String getDisplayName() {
        return displayName
    }

    /**
     * retrieves the real name of the player
     * @return the player's real name
     */
    public String getRealName() {
        return realName
    }

    /**
     * retrieves the active status of the player
     * @return true if the player is active false otherwise
     */
    public boolean isActive() {
        return isActive
    }

    /**
     * sets the active status of the player
     * @param status the new active status
     */
    public void setActive(boolean status) {
        this.isActive = status
        System.out.println("player " + displayName + " is now " + (isActive ? "active" : "inactive"))
    }

    /**
     * overrides the toString method to provide a readable string representation of the player
     * @return player details including display name email and active status
     */
    @Override
    public String toString() {
        return displayName + " (" + email + ") - " + (isActive ? "active" : "inactive")
    }
}
