/* (C)2025 */
package org.springpr.springpr.grpc.config;

import java.util.function.Supplier;

import com.google.common.annotations.VisibleForTesting;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtBearerHeaderClientInterceptor implements ClientInterceptor {
    private final Supplier<String> tokenSupplier;

    @VisibleForTesting
    static final Metadata.Key<String> BEARER_HEADER_KEY =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(BEARER_HEADER_KEY, "Bearer " + tokenSupplier.get());
                super.start(
                        new SimpleForwardingClientCallListener<RespT>(responseListener) {
                            @Override
                            public void onHeaders(Metadata headers) {
                                log.info("JwtBearerHeaderClientInterceptor - headers:{}", headers);
                                super.onHeaders(headers);
                            }
                        },
                        headers);
            }
        };
    }
}
