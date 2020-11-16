package tacos.entities.security;

import lombok.Data;
import tacos.entities.security.utlis.PasswordMatch;
import tacos.entities.security.utlis.UniqueUsername;

import javax.validation.constraints.NotBlank;

@Data
@PasswordMatch
public class RegistrationFormUser {

    @NotBlank
    @UniqueUsername
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirm;

    @NotBlank
    private String fullName;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String phoneNumber;

}
