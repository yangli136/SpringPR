/* (C)2023 */
package org.springpr.springpr.rest.base.resource;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.exception.RecoverableFailureException;
import org.springpr.springpr.base.exception.SpringPrApplicationException;
import org.springpr.springpr.base.logging.Log4jDiagnosticContextEnable;
import org.springpr.springpr.base.openapi.SwaggerView;
import org.springpr.springpr.base.stereotype.ValidationGroup;
import org.springpr.springpr.serviceinterface.resource.model.IdentifiableResourceDto;
import org.springpr.springpr.serviceinterface.resource.service.ResourceService;

@RestController
@RequestMapping(path = "/api/v1/resources")
@Validated
@AllArgsConstructor
@ConditionalOnBean(ResourceService.class)
@Slf4j
public class ResourceServiceController<T extends IdentifiableResourceDto> {
    @Qualifier("employeeServiceImpl") private final ResourceService<T> resourceServiceImpl;

    static long counter = 1;

    /* http://localhost:36866/emp/resources?page=2&size=20&sort=band,DESC&sort=id,ASC */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PageableAsQueryParam
    public Page<T> getAll(@NotNull Pageable pageable) {
        log.info("endpoint[get /resources] - get all resources, pageable:{}", pageable);
        return resourceServiceImpl.getAll(pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public T getbyId(@PathVariable("id") @NotBlank String id) {
        log.info("endpoint[get /resources/{id}] - finding employee with id:{}", id);
        return resourceServiceImpl.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @NotBlank String id) {
        log.info("endpoint[delete /resources/{id}] - deleting employee with id:{}", id);
        resourceServiceImpl.deleteById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(SwaggerView.NonHidden.class)
    @Log4jDiagnosticContextEnable
    @Timed(
            extraTags = {"cat", "web"},
            percentiles = {0.50, 0.95, 0.99},
            histogram = false)
    @Retryable(
            retryFor = {RecoverableFailureException.class},
            noRetryFor = {SpringPrApplicationException.class},
            maxAttempts = 6,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 1800000),
            listeners = {"retryLoggingListener"})
    public void add(@Validated(ValidationGroup.OnCreate.class) @RequestBody T resources) {
        resources.setId(String.valueOf(counter++));
        log.info("endpoint[post /resources] - creating employee with id:{}", resources.getId());
        resourceServiceImpl.saveOrUpdate(resources);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody T resources) {
        log.info("endpoint[put /resources] - updating employee with id:{}", resources.getId());
        resourceServiceImpl.saveOrUpdate(resources);
    }
}
