package backend.model;

public class User {
    private String username;
    private String password;
    private boolean isDaughter;


    public User(String username, String password, boolean isDaughter) {
        this.username = username;
        this.password = password;
        this.isDaughter = isDaughter;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDaughter() {
        return isDaughter;
    }

    public void setDaughter(boolean daughter) {
        isDaughter = daughter;
    }
}
