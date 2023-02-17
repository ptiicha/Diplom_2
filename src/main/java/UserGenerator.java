import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public static User getRandom() {
        return new User(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10));
    }
}
