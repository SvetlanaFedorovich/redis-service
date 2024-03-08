package mvp.project.redisservice.service.data;


import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

public class TestData {

    public static String cookieName = "user";
    public static String expected = "false";
    public static String invalidCookieName = "invalidUser";
    public static String sessionID = RandomStringUtils.randomAlphabetic(20);

}
