> Otel-Log4j2

---

# Spring Boot OpenTelemetry Integration with Log4j2

The document contains information about OpenTelementry integration with Log4j2 to deliver logs to Otel receiver and makes logs available in Otel OpenSearch Dashboards.

## Configurations

### Create OpenTelemetry SDK

Starting from:

> io.opentelemetry.instrumentation.spring.autoconfigure.OpenTelemetryAutoConfiguration

Please confirm that:

```
otel.sdk.disabled=false
```

In class OpenTelemetryAutoConfiguration, a static class **OpenTelemetrySdkConfig** is used to 


##### Create SdkLoggerProvider

sdkLoggerProvider requires two beans:
* list<LoggerExportersProvider> <- otelOtlpGrpcLogRecordExporter
    * created in io.opentelemetry.instrumentation.spring.autoconfigure.exporters.otlp.OtlpLoggerExporterAutoConfiguration
      * otel.exporter.otlp.enabled=true
      * otel.exporter.otlp.logs.enabled=true

if any of the properties is false, when OpenTelemetryAppender **append(LogEvent event)** is invoked, a **NoopLoggerBuilder** is returned from openTelemetry instance to OpenTelemetryAppender.

* otelResource
    * read from map properties starting with "otel.springboot.resource.attributes"
    * stored as Map<String, String> attributes.

Sample configuration of Attributes:

```
otel.springboot.resource.attributes.service.car.id=200005782
otel.springboot.resource.attributes.service.namespace=dev.springpr.springpr
otel.springboot.resource.attributes.service.instance.id=springpr-common-example
otel.springboot.resource.attributes.deployment.environment=E1
```

##### Create OpenTelemetry SDK
 
OpenTelemetry is created with sdkLoggerProvider above.

### Bind OpenTelemetry SDK to Log4j2 OpenTelemetry Appender

##### Create Log4j2 OpenTelemetry Appender

Please confirm that:

```
otel.instrumentation.log4j-appender.enabled=true
```

In class OpenTelemetryAppenderAutoConfiguration

> io.opentelemetry.instrumentation.spring.autoconfigure.instrumentation.logging.OpenTelemetryAppenderAutoConfiguration


* OpenTelemetry SDK bean (**openTelemetry**) will be injected into **OpenTelemetryAppender** configured in Log4j2.xml when Spring Application is ready (triggered by ApplicationReadyEvent).
* OpenTelemetryAppender uses **openTelemetry** bean to retrieve sdkLoggerProvider, then a LogRecordBuilder.
* **openTelemetry** bean also has a link to otelOtlpGrpcLogRecordExporter.

##### Inject span_id and trace_id into Log4j2 MDC

opentelemetry-log4j-context-data-2.17-autoconfigure has the following required provider:

> io.opentelemetry.instrumentation.log4j.contextdata.v2_17.OpenTelemetryContextDataProvider

Log4j2 will automatically pick up the above context provider if this library is in classpath. It gets current span and inject span_id and trace_id into MDC.

### OpenTelemetry Logging related properties

```
otel.exporter.logging.enabled=true
otel.exporter.logging.traces.enabled=true
otel.exporter.logging.metrics.enabled=true

otel.exporter.otlp.enabled=true
otel.exporter.otlp.logs.enabled=true
otel.instrumentation.log4j-appender.enabled=true
otel.exporter.otlp.logs.endpoint=https://otel.springpr.dev

otel.springboot.resource.attributes.service.car.id=200005782
otel.springboot.resource.attributes.service.namespace=dev.springpr.springpr
otel.springboot.resource.attributes.service.instance.id=springpr-common-example
otel.springboot.resource.attributes.deployment.environment=E1
```

### Log4j2 Configuration

A sample Log4j2.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" packages="io.opentelemetry.instrumentation.log4j.appender.v2_17">

  <Properties>
    <Property name="text.log.pattern">%d{yyyy-MM-HH:mm:ss.SSS} [%t] - %MDC{HOSTNAME,APPLICATION,CORRELATION_ID,MESSAGE_ID},trace_id=%MDC{traceId},span_id=%MDC{spanId} %highlight{%level}{FATAL=bg_red, ERROR=red, WARN=yellow, INFO=green, DEBUG=blue} %c{3} - %m%n</Property>
  </Properties>

  <Appenders>
    <Console name="console" target="SYSTEM_OUT">
      <PatternLayout pattern="${text.log.pattern}" />
    </Console>
    <OpenTelemetry name="OpenTelemetryAppender" captureMapMessageAttributes="true" captureExperimentalAttributes="true"/>
  </Appenders>

  <Loggers>
    <Root level="info" additivity="false">
      <AppenderRef ref="OpenTelemetryAppender" />
      <AppenderRef ref="console" />
    </Root>
  </Loggers>

</Configuration>
```

Please confirm
* **packages** value in **\<Configuration\>** tag
* **\<OpenTelemetry\>** definition
* **OpenTelemetryAppender** is used in Loggers

