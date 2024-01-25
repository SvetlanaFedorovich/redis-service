package mvp.project.redisservice.controller;

import lombok.RequiredArgsConstructor;
import mvp.project.redisservice.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RedisController {
    private final RedisService sessionService;

    @PostMapping("/send-sessionId")
    public void saveSessionId(@RequestBody Map.Entry<String, String> sessionId) {
        sessionService.saveSession(sessionId);
    }

    @GetMapping("/get-sessionId/{key}")
    public Boolean getSessionId(@PathVariable String key) {
        return sessionService.getValueByKey(key);
    }
}
