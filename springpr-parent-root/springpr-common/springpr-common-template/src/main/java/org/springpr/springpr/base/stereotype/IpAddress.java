/* (C)2023 */
package org.springpr.springpr.base.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import org.springpr.springpr.base.validation.IpAddressValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
@Documented
public @interface IpAddress {

    String message() default "{IpAddress.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
/**
 * Class level validation https://www.baeldung.com/spring-mvc-custom-validator
 *
 * <pre>
 * @FieldsValueMatch.List({
 *   @FieldsValueMatch(
 *     field = "password",
 *     fieldMatch = "verifyPassword",
 *     message = "Passwords do not match!"
 *   ),
 *   @FieldsValueMatch(
 *     field = "email",
 *     fieldMatch = "verifyEmail",
 *     message = "Email addresses do not match!"
 *   )
 * })
 * public class NewUserForm {
 * private String email;
 * private String verifyEmail;
 * private String password;
 * private String verifyPassword;
 *
 * // standard constructor, getters, setters
 * }
 * </pre>
 */
