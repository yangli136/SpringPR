/* (C)2025 */
package org.springpr.springpr.grpc.config;

import com.google.common.annotations.VisibleForTesting;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import lombok.extern.slf4j.Slf4j;

/** A interceptor to handle client header. */
@Slf4j
public class SampleHeaderClientInterceptor implements ClientInterceptor {

    @VisibleForTesting
    static final Metadata.Key<String> SAMPLE_HEADER_KEY =
            Metadata.Key.of("sample_client_header_key", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                /* put custom header */
                headers.put(SAMPLE_HEADER_KEY, "Sample_header_value");
                super.start(
                        new SimpleForwardingClientCallListener<RespT>(responseListener) {
                            @Override
                            public void onHeaders(Metadata headers) {
                                log.info("SampleHeaderClientInterceptor - headers:{}", headers);
                                super.onHeaders(headers);
                            }
                        },
                        headers);
            }
        };
    }
}
