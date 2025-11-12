/* (C)2025 */
package dev.springpr.springpr.grpc.example.hello.service;

import org.springframework.grpc.server.service.GrpcService;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.grpc.config.CustomGlobalServerInterceptor;
import dev.springpr.springpr.grpc.example.hello.proto.HelloReply;
import dev.springpr.springpr.grpc.example.hello.proto.HelloRequest;
import dev.springpr.springpr.grpc.example.hello.proto.SimpleGrpc;

// a bean of CustomGlobalServerInterceptor must be declared
@GrpcService(interceptors = CustomGlobalServerInterceptor.class)
@Slf4j
public class GrpcHelloService extends SimpleGrpc.SimpleImplBase {
    public static final String SERVICE_NAME = "grpc.v1.GrpcHelloService";

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Hello " + req.getName());
        if (req.getName().startsWith("error")) {
            throw new IllegalArgumentException("Bad name: " + req.getName());
        }
        if (req.getName().startsWith("internal")) {
            throw new RuntimeException();
        }
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void streamHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Hello " + req.getName());
        int count = 0;
        while (count < 10) {
            HelloReply reply =
                    HelloReply.newBuilder()
                            .setMessage("Hello(" + count + ") ==> " + req.getName())
                            .build();
            responseObserver.onNext(reply);
            count++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                responseObserver.onError(e);
                return;
            }
        }
        responseObserver.onCompleted();
    }
}
