/* (C)2023 */
package org.springpr.springpr.base.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import org.springpr.springpr.base.stereotype.ValidationGroup.OnCreate;
import org.springpr.springpr.base.stereotype.ValidationGroup.OnUpdate;

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
