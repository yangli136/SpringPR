/* (C)2023 */
package dev.springpr.springpr.grpc.example.hello.client.web;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.SettableFuture;

import io.grpc.StatusException;
import io.grpc.reflection.v1.ServerReflectionGrpc;
import io.grpc.reflection.v1.ServerReflectionRequest;
import io.grpc.reflection.v1.ServerReflectionResponse;
import io.grpc.stub.StreamObserver;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.grpc.example.hello.proto.HelloReply;
import dev.springpr.springpr.grpc.example.hello.proto.HelloRequest;
import dev.springpr.springpr.grpc.example.hello.proto.SimpleGrpc;

@Profile("client-e0")
@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
public class HelloController {
    private final SimpleGrpc.SimpleBlockingV2Stub simpleBlockingV2Stub;
    //    private final HealthGrpc.HealthBlockingV2Stub healthBlockingV2Stub;
    private final ServerReflectionGrpc.ServerReflectionStub serverReflectionBlockingV2Stub;

    @Observed(
            name = "helloController",
            contextualName = "hello",
            lowCardinalityKeyValues = {"operation", "sayHello"})
    @GetMapping(value = "/hello/{name}", produces = MediaType.TEXT_HTML_VALUE)
    public String hello(@PathVariable() final String name) {
        log.info("hello is called.");
        HelloReply response;
        try {
            response =
                    simpleBlockingV2Stub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return "<h1>SpringPR Base App running: response[" + response.getMessage() + "]</h1>";
        } catch (StatusException e) {
            log.info("exception:{}", e, e);
            return "<h1>failed with [" + e.getMessage() + "]</h1>";
        }
    }

    @Observed(
            name = "helloController",
            contextualName = "list",
            lowCardinalityKeyValues = {"operation", "serverReflectionInfo"})
    @GetMapping(value = "/list/{name}", produces = MediaType.TEXT_HTML_VALUE)
    public String reflection(@PathVariable() final String name) throws Exception {
        log.info("list is called.");

        SettableFuture<List<String>> responseListFuture = SettableFuture.create();
        StreamObserver<ServerReflectionRequest> requestObserver =
                serverReflectionBlockingV2Stub.serverReflectionInfo(
                        new StreamObserver<ServerReflectionResponse>() {
                            private final List<String> responseList = new ArrayList<>();

                            @Override
                            public void onNext(ServerReflectionResponse response) {
                                // Process the reflection response
                                log.error("****** aaa");
                                List<String> resp;
                                if (response.hasListServicesResponse()) {
                                    resp =
                                            response
                                                    .getListServicesResponse()
                                                    .getServiceList()
                                                    .stream()
                                                    .map(i -> i.getName())
                                                    .toList();
                                    log.error("****** {}", resp);
                                    responseList.addAll(resp);
                                } else if (response.hasFileDescriptorResponse()) {
                                    resp =
                                            response
                                                    .getFileDescriptorResponse()
                                                    .getFileDescriptorProtoList()
                                                    .stream()
                                                    .map(i -> i.toString(StandardCharsets.US_ASCII))
                                                    .toList();
                                    log.error("****** {}", resp);
                                    responseList.addAll(resp);
                                } else {
                                    log.error("******  bbbb");
                                    throw new UnsupportedOperationException(
                                            "operation not supported.");
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                log.error("Reflection error: " + t.getMessage());
                                responseListFuture.setException(t);
                            }

                            @Override
                            public void onCompleted() {
                                log.error("Reflection stream completed.");
                                responseListFuture.set(responseList);
                            }
                        });

        if (name.contains("list")) {
            requestObserver.onNext(
                    ServerReflectionRequest.newBuilder().setListServices("").build());
        } else {
            requestObserver.onNext(
                    ServerReflectionRequest.newBuilder().setFileContainingSymbol(name).build());
        }

        requestObserver.onCompleted();

        log.error("#################{}");
        List<String> responseList = responseListFuture.get();

        return "<h1>SpringPR Base App running: response[" + responseList + "]</h1>";
    }
}
