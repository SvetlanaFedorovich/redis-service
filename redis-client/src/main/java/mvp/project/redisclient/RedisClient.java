package mvp.project.redisclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${spring.data.redis.port}",
        url = "${spring.data.redis.host}"
)
public interface RedisClient {

    @GetMapping("/api/get-sessionId/{key}")
    Boolean getSessionId(@PathVariable String key);
}
