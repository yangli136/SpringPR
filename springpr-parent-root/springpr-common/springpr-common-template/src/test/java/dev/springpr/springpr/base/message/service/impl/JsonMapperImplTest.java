/* (C)2023 */
package dev.springpr.springpr.base.message.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.json.SpringPrJsonMapper;
import dev.springpr.springpr.base.json.impl.SpringPrJsonMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class JsonMapperImplTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testStringList() {
        SpringPrJsonMapper<String, String> mapper = new SpringPrJsonMapperImpl<>(this.objectMapper);
        List<String> list = mapper.getList("[\"A\", \"B\"]", String.class);
        log.info("list:{}", list);
        assertEquals(2, list.size());
    }

    @Test
    void testIntegerList() {
        SpringPrJsonMapper<String, Integer> mapper =
                new SpringPrJsonMapperImpl<>(this.objectMapper);
        List<Integer> list = mapper.getList("[1, 2]", Integer.class);
        log.info("list:{}", list);
        assertEquals(2, list.size());
    }

    @Test
    void testCarList() {
        SpringPrJsonMapper<String, Car> mapper = new SpringPrJsonMapperImpl<>(this.objectMapper);
        String jsonCarListAsString =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" :"
                        + " \"FIAT\" }]";
        List<Car> list = mapper.getList(jsonCarListAsString, Car.class);
        log.info("list:{}", list);
        assertEquals(2, list.size());
    }

    @Test
    void testCarMap() {
        SpringPrJsonMapper<String, String> mapper = new SpringPrJsonMapperImpl<>(this.objectMapper);
        String jsonCarMapAsString = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        @SuppressWarnings("unchecked")
        Map<String, String> map =
                mapper.getMap(
                        jsonCarMapAsString,
                        (@NotNull Class<? extends Map<String, String>>)
                                (new HashMap<>().getClass()),
                        String.class,
                        String.class);
        log.info("map:{}", map);
        assertEquals(2, map.keySet().size());
    }

    @Test
    void testGetEntity() {
        SpringPrJsonMapper<String, Car> mapper = new SpringPrJsonMapperImpl<>(this.objectMapper);
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car = mapper.getEntity(json, Car.class);
        log.info("car:{}", car);
        assertEquals("Black", car.getColor());
        assertEquals("BMW", car.getType());
    }

    @Test
    void testgetJsonString() {
        SpringPrJsonMapper<String, Car> mapper = new SpringPrJsonMapperImpl<>(this.objectMapper);
        Car car = new Car("Yellow", "BMW");
        String json = mapper.getJsonString(car);
        log.info("json:{}", json);
        assertEquals("{\"color\":\"Yellow\",\"type\":\"BMW\"}", json);
    }
}
