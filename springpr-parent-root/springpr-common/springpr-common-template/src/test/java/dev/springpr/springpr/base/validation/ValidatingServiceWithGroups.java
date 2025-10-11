/* (C)2023 */
package dev.springpr.springpr.base.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import dev.springpr.springpr.base.stereotype.ValidationGroup.OnCreate;
import dev.springpr.springpr.base.stereotype.ValidationGroup.OnUpdate;

@Service
@Validated
class ValidatingServiceWithGroups {

    @Validated(OnCreate.class)
    void validateForCreate(@Valid InputWithCustomValidator input) {
        // do something
    }

    @Validated(OnUpdate.class)
    void validateForUpdate(@Valid InputWithCustomValidator input) {
        // do something
    }
}
