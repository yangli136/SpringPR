/* (C)2023 */
package org.springpr.springpr.serviceinterface.resource.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springpr.springpr.serviceinterface.resource.model.IdentifiableResourceDto;

public interface ResourceService<T extends IdentifiableResourceDto> {
    T getById(@NotBlank String id);

    Mono<T> findById(@NotBlank String id);

    String saveOrUpdate(@Valid T employee);

    void deleteById(@NotBlank String id);

    List<T> getFirst3ByOrderBetween(@Min(0) int minOrder, @Min(0) int maxOrder);

    Page<T> getAll(@NotNull Pageable pageable);

    Flux<T> getAll();

    void evictAll();
}
