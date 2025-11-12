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
import dev.springpr.springpr.yugabytedb.crmd.model.Merchant;
import dev.springpr.springpr.yugabytedb.crmd.repository.MerchantRepository;
import dev.springpr.springpr.yugabytedb.example.BadRequestException;
import dev.springpr.springpr.yugabytedb.example.MerchantNotFoundException;

@RestController
@RequestMapping(path = "/api/v1/merchant")
@Validated
@RequiredArgsConstructor
@Slf4j
public class MerchantController {
    private final MerchantRepository merchantRepository;
    private final ObjectMapper objectMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Observed(
            name = "webclient.merchant",
            contextualName = "saveMerchant",
            lowCardinalityKeyValues = {"userType", "userType6"})
    @Retryable(
            retryFor = {RecoverableFailureException.class},
            maxAttempts = 6,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 1800000),
            listeners = {"retryLoggingListener"})
    public ResponseEntity<String> addMerchant(
            @Validated(ValidationGroup.OnCreate.class) @RequestBody String merchantJson) {
        log.info("endpoint[post /api/v1/merchant] - creating Merchant:{}", merchantJson);
        JsonNode merchantJsonNode = null;
        try {
            merchantJsonNode = objectMapper.readTree(merchantJson);
        } catch (JsonMappingException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new BadRequestException(merchantJson);
        } catch (JsonProcessingException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new BadRequestException(merchantJson);
        }
        String merId = "merId is null";
        try {
            merId = merchantJsonNode.get("merId").asText();
        } catch (NullPointerException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new BadRequestException(merchantJson);
        }
        final Merchant merchant = new Merchant();
        merchant.setMerId(merId);
        merchant.setDocument(merchantJsonNode);
        LocalDateTime now = LocalDateTime.now();
        merchant.setCreateTimestamp(now);
        merchant.setLastUpdateTimestamp(now);
        try {
            merchantRepository.save(merchant);
        } catch (RuntimeException e) {
            log.error("exception:{}", e.getMessage(), e);
            throw new RecoverableFailureException(merchantJson);
        }
        return new ResponseEntity<>("{ \"merId\": \"" + merId + "\"}", HttpStatus.CREATED);
    }

    @GetMapping(value = "{merId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Observed(
            name = "webclient.merchant",
            contextualName = "getMerchant",
            lowCardinalityKeyValues = {"userType", "userType6"})
    @Retryable(
            retryFor = {RecoverableFailureException.class},
            maxAttempts = 6,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 1800000),
            listeners = {"retryLoggingListener"})
    public ResponseEntity<String> getById(@PathVariable("merId") @NotBlank String merId) {
        final Optional<Merchant> merchantOptional = merchantRepository.findById(merId);
        if (merchantOptional.isPresent()) {
            final JsonNode document = merchantOptional.get().getDocument();
            return new ResponseEntity<>(document.toString(), HttpStatus.OK);
        }
        throw new MerchantNotFoundException(merId);
    }
}
