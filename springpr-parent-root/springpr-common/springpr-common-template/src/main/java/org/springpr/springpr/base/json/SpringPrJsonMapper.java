/* (C)2023 */
package org.springpr.springpr.base.json;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface SpringPrJsonMapper<K, E> {
    E getEntity(@NotBlank String json, @NotNull Class<E> entityType);

    String getJsonString(@NotNull E entity);

    List<E> getList(@NotBlank String jsonList, @NotNull Class<E> elementType);

    Map<K, E> getMap(
            @NotBlank String jsonMap,
            @NotNull Class<? extends Map<K, E>> mapClass,
            @NotNull Class<K> keyClass,
            @NotNull Class<E> valueClass);
}
