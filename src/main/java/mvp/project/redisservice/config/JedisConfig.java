package mvp.project.redisservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {

        @Bean
        public Jedis jedis() {
            return new Jedis("minikube.mshome.net", 31300);
        }
    }
