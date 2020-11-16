package tacos.entities.security.utlis;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordAndConfirmValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PasswordMatch {

    public String message() default "Passwords are not equal";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
