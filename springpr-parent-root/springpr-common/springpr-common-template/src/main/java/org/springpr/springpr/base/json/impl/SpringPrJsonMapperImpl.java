/* (C)2023 */
package org.springpr.springpr.base.json.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springpr.springpr.base.exception.SpringPrBaseAlertMessage;
import org.springpr.springpr.base.exception.TransformationFailureException;
import org.springpr.springpr.base.json.SpringPrJsonMapper;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class SpringPrJsonMapperImpl<K, E> implements SpringPrJsonMapper<K, E> {

    @SuppressWarnings("squid:S3749")
    private final ObjectMapper jacksonObjectMapper;

    @Override
    public List<E> getList(@NotBlank final String jsonList, @NotNull Class<E> elementType) {
        log.info("jsonListAsString:{}", jsonList);
        List<E> result = emptyList();
        try {
            result =
                    jacksonObjectMapper.readValue(
                            jsonList,
                            TypeFactory.defaultInstance()
                                    .constructCollectionType(List.class, elementType));
        } catch (JsonProcessingException e) {
            throw new TransformationFailureException(
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_JSON_MSG_PARSING_EXCEPTION
                            + " ### ### ### Failed to transfer to a list of"
                            + elementType
                            + " from:"
                            + jsonList,
                    e);
        }
        log.info("result:{}", result);
        return result;
    }

    @Override
    public E getEntity(@NotBlank String json, @NotNull Class<E> entityType) {
        try {
            return this.jacksonObjectMapper.readValue(json, entityType);
        } catch (JsonProcessingException e) {
            throw new TransformationFailureException(
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_JSON_MSG_PARSING_EXCEPTION
                            + " ### ### ### Failed to map to "
                            + entityType
                            + "  from:"
                            + json,
                    e);
        }
    }

    @Override
    public String getJsonString(@NotNull E entity) {
        try {
            return this.jacksonObjectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new TransformationFailureException(
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_JSON_MSG_PARSING_EXCEPTION
                            + " ### ### ### Failed to map to Json String from entity:"
                            + entity,
                    e);
        }
    }

    @Override
    public Map<K, E> getMap(
            @NotBlank String jsonMap,
            @NotNull Class<? extends Map<K, E>> mapClass,
            @NotNull Class<K> keyClass,
            @NotNull Class<E> valueClass) {
        log.info("jsonMap:{}", jsonMap);
        Map<K, E> result = emptyMap();
        try {
            result =
                    jacksonObjectMapper.readValue(
                            jsonMap,
                            TypeFactory.defaultInstance()
                                    .constructMapLikeType(mapClass, keyClass, valueClass));
        } catch (JsonProcessingException e) {
            throw new TransformationFailureException(
                    SpringPrBaseAlertMessage.SPRINGPR_BASE_APP_JSON_MSG_PARSING_EXCEPTION
                            + " ### ### ### Failed to transfer to a map of key class: "
                            + keyClass
                            + ", value class:"
                            + valueClass
                            + " from:"
                            + jsonMap,
                    e);
        }
        log.info("result:{}", result);
        return result;
    }
}
