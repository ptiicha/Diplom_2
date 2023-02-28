import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_EMAIL_LENGTH = 10;
    private static final int MAX_PASSWORD_LENGTH = 15;

    public static User getRandomUser() {
        User user = new User();
        user.setName(getRandomName());
        user.setEmail(getRandomEmail());
        user.setPassword(getRandomPassword());
        return user;
    }

    private static String getRandomString(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String getRandomName() {
        return getRandomString(MAX_NAME_LENGTH);
    }

    public static String getRandomEmail() {
        return getRandomString(MAX_EMAIL_LENGTH / 2) + "@" + getRandomString(MAX_EMAIL_LENGTH / 2) + "." + getRandomString(2);
    }

    public static String getRandomPassword() {
        return getRandomString(MAX_PASSWORD_LENGTH);
    }
}
