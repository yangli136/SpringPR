package com.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.73.0)",
    comments = "Source: proto/service/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GetDataGrpc {

  private GetDataGrpc() {}

  public static final java.lang.String SERVICE_NAME = "service.GetData";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.service.GetDataRequest,
      com.service.GetDataResponse> getGetDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetData",
      requestType = com.service.GetDataRequest.class,
      responseType = com.service.GetDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.service.GetDataRequest,
      com.service.GetDataResponse> getGetDataMethod() {
    io.grpc.MethodDescriptor<com.service.GetDataRequest, com.service.GetDataResponse> getGetDataMethod;
    if ((getGetDataMethod = GetDataGrpc.getGetDataMethod) == null) {
      synchronized (GetDataGrpc.class) {
        if ((getGetDataMethod = GetDataGrpc.getGetDataMethod) == null) {
          GetDataGrpc.getGetDataMethod = getGetDataMethod =
              io.grpc.MethodDescriptor.<com.service.GetDataRequest, com.service.GetDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.service.GetDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.service.GetDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GetDataMethodDescriptorSupplier("GetData"))
              .build();
        }
      }
    }
    return getGetDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GetDataStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GetDataStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GetDataStub>() {
        @java.lang.Override
        public GetDataStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GetDataStub(channel, callOptions);
        }
      };
    return GetDataStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static GetDataBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GetDataBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GetDataBlockingV2Stub>() {
        @java.lang.Override
        public GetDataBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GetDataBlockingV2Stub(channel, callOptions);
        }
      };
    return GetDataBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GetDataBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GetDataBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GetDataBlockingStub>() {
        @java.lang.Override
        public GetDataBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GetDataBlockingStub(channel, callOptions);
        }
      };
    return GetDataBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GetDataFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GetDataFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GetDataFutureStub>() {
        @java.lang.Override
        public GetDataFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GetDataFutureStub(channel, callOptions);
        }
      };
    return GetDataFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getData(com.service.GetDataRequest request,
        io.grpc.stub.StreamObserver<com.service.GetDataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GetData.
   */
  public static abstract class GetDataImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GetDataGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GetData.
   */
  public static final class GetDataStub
      extends io.grpc.stub.AbstractAsyncStub<GetDataStub> {
    private GetDataStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDataStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GetDataStub(channel, callOptions);
    }

    /**
     */
    public void getData(com.service.GetDataRequest request,
        io.grpc.stub.StreamObserver<com.service.GetDataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GetData.
   */
  public static final class GetDataBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<GetDataBlockingV2Stub> {
    private GetDataBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDataBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GetDataBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.service.GetDataResponse getData(com.service.GetDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service GetData.
   */
  public static final class GetDataBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GetDataBlockingStub> {
    private GetDataBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDataBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GetDataBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.service.GetDataResponse getData(com.service.GetDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GetData.
   */
  public static final class GetDataFutureStub
      extends io.grpc.stub.AbstractFutureStub<GetDataFutureStub> {
    private GetDataFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDataFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GetDataFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.service.GetDataResponse> getData(
        com.service.GetDataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DATA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_DATA:
          serviceImpl.getData((com.service.GetDataRequest) request,
              (io.grpc.stub.StreamObserver<com.service.GetDataResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetDataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.service.GetDataRequest,
              com.service.GetDataResponse>(
                service, METHODID_GET_DATA)))
        .build();
  }

  private static abstract class GetDataBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GetDataBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.service.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GetData");
    }
  }

  private static final class GetDataFileDescriptorSupplier
      extends GetDataBaseDescriptorSupplier {
    GetDataFileDescriptorSupplier() {}
  }

  private static final class GetDataMethodDescriptorSupplier
      extends GetDataBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GetDataMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GetDataGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GetDataFileDescriptorSupplier())
              .addMethod(getGetDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
