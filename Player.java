public class Player {
    private String email;
    private String displayName;
    private String realName;

    public Player(String email, String displayName, String realName) {
        this.email = email;
        this.displayName = displayName;
        this.realName = realName;
    }

    public String getEmail() { return email; }
    public String getDisplayName() { return displayName; }
    public String getRealName() { return realName; }
}
