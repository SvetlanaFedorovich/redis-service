package mvp.project.redisservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class RedisService {

    private final Jedis jedis;
    public Boolean getValueByKey(String key) {
        return Boolean.TRUE.equals(jedis.exists(key));
    }

    public void saveSession(Map.Entry<String, String> sessionId) {
        jedis.setex(sessionId.getKey(), 3600, sessionId.getValue());
    }
}
