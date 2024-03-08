package mvp.project.redisservice.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import mvp.project.authservice.CookieRequest;
import mvp.project.authservice.CookieResponse;
import mvp.project.authservice.RedisServiceGrpc;
import mvp.project.authservice.SessionRequest;
import net.devh.boot.grpc.server.service.GrpcService;
import redis.clients.jedis.exceptions.JedisConnectionException;

import static mvp.project.redisservice.builder.RedisCookieResponseBuilder.redisCookieResponseBuild;

@GrpcService
@RequiredArgsConstructor
public class RedisGrpcService extends RedisServiceGrpc.RedisServiceImplBase {

    private final RedisDbService redisDbService;

    @Override
    public void saveSessionID(CookieRequest request, StreamObserver<CookieResponse> responseObserver) {
        try {
            CookieResponse cookieResponse = redisCookieResponseBuild(redisDbService.saveSessionID(request.getName(), request.getValue()));
            responseObserver.onNext(cookieResponse);
            responseObserver.onCompleted();
        } catch (JedisConnectionException e) {
            responseObserver.onError(Status.UNAVAILABLE.withDescription(e.getMessage()).asRuntimeException());
        }
    }
    @Override
    public void getSessionID(SessionRequest request, StreamObserver<CookieResponse> responseObserver) {
        try {
            CookieResponse cookieResponse = redisCookieResponseBuild(redisDbService.getSessionID(request.getUsername()));
            responseObserver.onNext(cookieResponse);
            responseObserver.onCompleted();
        } catch (JedisConnectionException e) {
            responseObserver.onError(Status.UNAVAILABLE.withDescription(e.getMessage()).asRuntimeException());
        }
    }
}
