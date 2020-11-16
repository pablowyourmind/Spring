package tacos.entities.security.utlis;

import org.springframework.beans.factory.annotation.Autowired;
import tacos.data.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UniqueValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Objects.isNull(userRepository.findByUsername(value));
    }
}
