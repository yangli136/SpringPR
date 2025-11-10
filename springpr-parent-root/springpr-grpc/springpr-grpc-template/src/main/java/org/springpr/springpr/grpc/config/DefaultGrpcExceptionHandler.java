/* (C)2025 */
package org.springpr.springpr.grpc.config;

import org.springframework.grpc.server.exception.GrpcExceptionHandler;

import io.grpc.Status;
import io.grpc.StatusException;

public class DefaultGrpcExceptionHandler implements GrpcExceptionHandler {

    @Override
    public StatusException handleException(Throwable exception) {
        return new StatusException(Status.UNKNOWN, Status.trailersFromThrowable(exception));
    }
}
