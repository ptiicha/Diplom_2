public class UserCredentials {
    private String login;
    private String password;
    private String userName;

    public UserCredentials(String login, String password, String userName) {
        this.login = login;
        this.password = password;
        this.userName = userName;
    }

    public static UserCredentials from(User user) {
        return new UserCredentials(user.getLogin(), user.getPassword(), user.getUserName());
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public UserCredentials() {
    }
}
