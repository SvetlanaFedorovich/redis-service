package mvp.project.redisservice.model.response;

import lombok.Builder;

@Builder
public record RedisCookieResponse(String sessionID) {
}
