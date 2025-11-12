/* (C)2025 */
package dev.springpr.springpr.yugabytedb.crmd.web;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.observation.annotation.Observed;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import dev.springpr.springpr.base.exception.RecoverableFailureException;
import dev.springpr.springpr.base.stereotype.ValidationGroup;
import dev.springpr.springpr.yugabytedb.crmd.model.Industry;
import dev.springpr.springpr.yugabytedb.crmd.repository.IndustryRepository;
import dev.springpr.springpr.yugabytedb.example.BadRequestException;
import dev.springpr.springpr.yugabytedb.example.IndustryNotFoundException;

@RestController
@RequestMapping(path = "/api/v1/industry")
@Validated
@RequiredArgsConstructor
@Slf4j
public class IndustryController {
    private final IndustryRepository industryRepository;
    private final ObjectMapper objectMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Observed(
            name = "webclient.industry",
            contextualName = "saveIndustry",
            lowCardinalityKeyValues = {"userType", "userType6"})
    @Retryable(
            retryFor = {RecoverableFailureException.class},
            maxAttempts = 6,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 1800000),
            listeners = {"retryLoggingListener"})
    public ResponseEntity<String> addIndustry(
            @Validated(ValidationGroup.OnCreate.class) @RequestBody String industryJson) {
        log.info("endpoint[post /api/v1/industry] - creating Industry:{}", industryJson);
        JsonNode industryJsonNode = null;
        try {
            industryJsonNode = objectMapper.readTree(industryJson);
        } catch (JsonMappingException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new BadRequestException(industryJson);
        } catch (JsonProcessingException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new BadRequestException(industryJson);
        }
        String sic8Cd = "sic8Cd is null";
        try {
            sic8Cd = industryJsonNode.get("sic8Cd").asText();
        } catch (NullPointerException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new BadRequestException(industryJson);
        }
        final Industry industry = new Industry();
        industry.setSic8Cd(sic8Cd);
        industry.setDocument(industryJsonNode);
        LocalDateTime now = LocalDateTime.now();
        industry.setCreateTimestamp(now);
        industry.setLastUpdateTimestamp(now);
        try {
            industryRepository.save(industry);
        } catch (RuntimeException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new RecoverableFailureException(industryJson);
        }
        return new ResponseEntity<>("{ \"merId\": \"" + sic8Cd + "\"}", HttpStatus.CREATED);
    }

    @GetMapping(value = "{sic8Cd}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable("sic8Cd") @NotBlank String sic8Cd) {
        final Optional<Industry> industryOptional = industryRepository.findById(sic8Cd);
        if (industryOptional.isPresent()) {
            final JsonNode document = industryOptional.get().getDocument();
            return new ResponseEntity<>(document.toString(), HttpStatus.OK);
        }
        throw new IndustryNotFoundException(sic8Cd);
    }
}
