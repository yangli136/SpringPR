/* (C)2023 */
package dev.springpr.springpr.base.health.file;

import jakarta.validation.constraints.NotBlank;

public interface SpringPrBaseFileValidationService {

    boolean isFileExists(@NotBlank String absolutePathToFile);
}
