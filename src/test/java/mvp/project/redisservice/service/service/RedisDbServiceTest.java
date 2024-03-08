package mvp.project.redisservice.service.service;

import mvp.project.redisservice.service.RedisTestContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static mvp.project.redisservice.service.RedisTestContainer.REDIS_CONTAINER;
import static mvp.project.redisservice.service.RedisTestContainer.jedis;
import static mvp.project.redisservice.service.RedisTestContainer.redisDbService;
import static mvp.project.redisservice.service.data.TestData.cookieName;
import static mvp.project.redisservice.service.data.TestData.expected;
import static mvp.project.redisservice.service.data.TestData.invalidCookieName;
import static mvp.project.redisservice.service.data.TestData.sessionID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({RedisTestContainer.class})
class RedisDbServiceTest {

    @Test
    void whenCheckingRunningStatus_thenStatusIsRunning() {
        assertTrue(REDIS_CONTAINER.isRunning());
    }

    @Test
    public void whenSaveSessionIDInvokedWithValidData_thenSessionIDIsReturned() {
        assertThat(redisDbService.saveSessionID(cookieName, sessionID)).isEqualTo("OK");
        assertThat(3600L).isEqualTo(jedis.ttl(cookieName));
        assertThat(redisDbService.getSessionID(cookieName)).isEqualTo(sessionID);
    }

    @Test
    public void whenGetSessionIDInvokedWithNotValidData_thenSessionIDIsReturned() {
        redisDbService.saveSessionID(cookieName, sessionID);
        assertThat(redisDbService.getSessionID(cookieName)).isEqualTo(sessionID);
        assertThat(redisDbService.getSessionID(invalidCookieName)).isEqualTo(expected);
        assertThat(redisDbService.getSessionID(null)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    public void whenSaveSessionIDInvokedWithNull_thenThrowsIsReturned(String input) {
        assertThat(redisDbService.saveSessionID(input, sessionID)).isEqualTo(expected);
        assertThat(redisDbService.saveSessionID(cookieName, input)).isEqualTo(expected);
    }

    @Test
    public void whenGetSessionIDInvokedWithExpiredSessionId_thenThrowsIsReturned() {
         redisDbService.getJedis().setex(cookieName, 1, sessionID);
        assertThat(redisDbService.getSessionID(cookieName)).isEqualTo(sessionID);
        try {
            Thread.sleep((2000));
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        assertThat(redisDbService.getSessionID(cookieName)).isEqualTo(expected);
    }
}