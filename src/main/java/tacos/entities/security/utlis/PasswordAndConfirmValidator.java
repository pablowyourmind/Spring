package tacos.entities.security.utlis;

import tacos.entities.security.RegistrationFormUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordAndConfirmValidator implements ConstraintValidator<PasswordMatch, RegistrationFormUser> {
    @Override
    public boolean isValid(RegistrationFormUser value, ConstraintValidatorContext context) {
        return value.getPassword().equalsIgnoreCase(value.getConfirm());
    }
}
