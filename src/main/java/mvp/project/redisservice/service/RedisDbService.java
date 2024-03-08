package mvp.project.redisservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


@Service
@Getter
@RequiredArgsConstructor
public class RedisDbService {

    private final Jedis jedis;
    public String getSessionID(String username) {
        return (username != null && jedis.exists(username)) ?
                jedis.get(username) : String.valueOf(false);
    }

    public String saveSessionID(String cookieName, String sessionID) {
        return  (cookieName != null && sessionID != null) ?
                jedis.setex(cookieName, 3600, sessionID) : String.valueOf(false);
    }
}
