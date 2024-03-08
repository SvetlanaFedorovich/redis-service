package mvp.project.redisservice.builder;

import mvp.project.authservice.CookieResponse;


public class RedisCookieResponseBuilder {
    public static CookieResponse redisCookieResponseBuild(String sessionID) {
        return CookieResponse.newBuilder()
                .setResult(sessionID)
                .build();
    }
}
