/* (C)2023 */
package org.springpr.springpr.base.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IpAddressValidatorTest {

    @Test
    void test() {
        IpAddressValidator validator = new IpAddressValidator();
        Assertions.assertTrue(validator.isValid("111.111.111.111", null));
        Assertions.assertFalse(validator.isValid("111.foo.111.111", null));
        Assertions.assertFalse(validator.isValid("111.111.256.111", null));
    }
}
