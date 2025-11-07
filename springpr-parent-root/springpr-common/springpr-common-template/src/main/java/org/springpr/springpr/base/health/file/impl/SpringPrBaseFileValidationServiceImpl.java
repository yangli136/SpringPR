/* (C)2023 */
package org.springpr.springpr.base.health.file.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.FileSystemResource;

import jakarta.validation.constraints.NotBlank;

import org.springpr.springpr.base.health.file.SpringPrBaseFileValidationService;
import org.springpr.springpr.base.stereotype.ValidatedService;

@ValidatedService
@ConditionalOnProperty(prefix = "required", name = "file.check.enabled", havingValue = "true")
public class SpringPrBaseFileValidationServiceImpl implements SpringPrBaseFileValidationService {

    @Override
    public boolean isFileExists(@NotBlank final String absolutePathToFile) {
        final FileSystemResource fileResource = new FileSystemResource(absolutePathToFile);
        return fileResource.exists();
    }
}
