> Otel-Traces

---

# Spring Boot OpenTelemetry Integration - Enable Traces

The document contains information about OpenTelementry integration to make traces available in ELF Tracing UI.

## Configurations

### Create OpenTelemetry SDK

Starting from:

> io.opentelemetry.instrumentation.spring.autoconfigure.OpenTelemetryAutoConfiguration

Please confirm that:

```
otel.sdk.disabled=false
```

In class OpenTelemetryAutoConfiguration, a static class **OpenTelemetrySdkConfig** is used to 

##### Create SdkTracerProvider
            
sdkTracerProvider requires three beans:

* SamplerProperties
    * otel.traces.sampler.probability [0.0, 1.0]
* List<SpanExporter>
    * OtlpGrpcSpanExporter <- otelOtlpGrpcSpanExporter
* otelResource
    * read from map properties starting with "otel.springboot.resource.attributes"
    * stored as Map<String, String> attributes.

A **BatchSpanProcessor** is created, it batches spans exported by the SDK then pushes them to the exporter pipeline.

> tracerProviderBuilder.addSpanProcessor(batchSpanProcessor)

**The following shows the relationships between important entities involved**

> sdkTracerProvider (TracerSharedState sharedState (batchSpanProcessor), ComponentRegistry<SdkTracer> tracerSdkComponentRegistry)
>    -> SdkTracer ((tracerSdkComponentRegistry) -> sharedState)
>    -> SdkSpan ((sharedState) -> SpanProcessor)

**How spans are collected and exported**

> Span.end() -> batchSpanProcessor.onEnd(span) -> batchSpanProcessor.woker.addSpan(span) -> batchSpanProcessor.worker.queue.offer(span)
> batchSpanProcessor.exportCurrentBatch() run in a daemon thread every scheduleDelayNanos. 


##### Create Otlp(OpenTelemetry Protocol) Span Exporter

> io.opentelemetry.instrumentation.spring.autoconfigure.exporters.otlp.OtlpSpanExporterAutoConfiguration

```
otel.exporter.otlp.enabled=true
otel.exporter.otlp.traces.enabled=true
```

if any of the values above is false, then **NoopSpanProcessor** will be added.

> OtlpGrpcSpanExporterBuilder (OtlpExporterProperties) -> OtlpGrpcSpanExporter -> GrpcExporterBuilder -> GrpcExporter

##### ObservationHandler Configuration to be used by application

> org.springframework.boot.actuate.autoconfigure.tracing.MicrometerTracingAutoConfiguration

* PropagatingSenderTracingObservationHandler
* PropagatingReceiverTracingObservationHandler
* DefaultTracingObservationHandler

The above handlers have Tracer and Propagator instances in them. The handlers are invoked by Observation instance.

> SimpleObservationRegistry -> Observation.start() -> handler.onStart() -> span.start() -> endSpan(span)

**SpanAspect** is also configured.

##### create micrometerOtelTracer

> org.springframework.boot.actuate.autoconfigure.tracing.OpenTelemetryAutoConfiguration

The following beans are used by handlers above

* otelTracerEventPublisher
    * otelSlf4JBaggageEventListener
    * otelSlf4JEventListener

* micrometerOtelTracer
    * tracer -> otelTracer(openTelemetry) <- openTelemetry.getTracer()
    * eventPublisher -> otelTracerEventPublisher
    * otelCurrentTraceContext

##### Create Observation Registry

> org.springframework.boot.actuate.autoconfigure.observation.ObservationAutoConfiguration

**ObservationRegistry** and **observedAspect** are configured.

> **Micrometer observation API encapsulate both Metrics and Traces functions**
> 
>  observedAspect -> observationRegistry
> 
> 
>  org.springframework.boot.actuate.autoconfigure.observation.ObservationAutoConfiguration
>  observationRegistry -> ObservationHandlers -> {DefaultMeterObservationHandler,
>  DefaultTracingObservationHandler
> 
>  How the observation is connected to Metrics and Traces
> 
```
    ObservationRegistry registry = ObservationRegistry.create();
    registry.observationConfig()
    // assuming that micrometer-core is on the classpath
        .observationHandler(new DefaultMeterObservationHandler(meterRegistry))
    // we set up a first matching handler that creates spans - it comes from
    // Micrometer
    // Tracing. We set up spans for sending and receiving data over the wire
    // and a default one
        .observationHandler(new ObservationHandler.FirstMatchingCompositeObservationHandler(
                new PropagatingSenderTracingObservationHandler< (tracer, propagator),
                new PropagatingReceiverTracingObservationHandler< (tracer, propagator),
                new DefaultTracingObservationHandler(tracer)));
```
>  Observation integation out-of-box:
>      Apache Camel
>      gRPC
>      JDBC
>      JDK Http Client
>      JMS
>      OkHttp
>      Reactor
>      Reactor Netty
>      Spring Batch
>      Spring Data Cassandra
>      Spring Data MongoDB
>      Spring Data Redis
>      Spring GraphQL
>      Spring Integration
>      Spring Kafka
>      Spring Modulith
>      Spring MVC
>      Spring WebFlux
> 

##### Create OpenTelemetry 
 
OpenTelemetry is created with sdkLoggerProvider above.

Sample configuration of Attributes:

```
otel.springboot.resource.attributes.service.car.id=200005782
otel.springboot.resource.attributes.service.namespace=dev.springpr.springpr
otel.springboot.resource.attributes.service.instance.id=springpr-common-example
otel.springboot.resource.attributes.deployment.environment=E1
```

### OpenTelemetry Tracing related properties

```
otel.exporter.otlp.enabled=true
otel.exporter.otlp.traces.enabled=true
otel.exporter.otlp.traces.endpoint=https://otel.springpr.dev
otel.traces.sampler.probability=1.0
# Batch Span Processor
otel.bsp.schedule.delay=5000
otel.bsp.max.queue.size=2048
otel.bsp.max.export.batch.size=512
otel.bsp.export.timeout=30000

otel.springboot.resource.attributes.service.car.id=200005782
otel.springboot.resource.attributes.service.namespace=dev.springpr.springpr
otel.springboot.resource.attributes.service.instance.id=springpr-common-example
otel.springboot.resource.attributes.deployment.environment=E1
```
