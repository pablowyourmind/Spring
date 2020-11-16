package tacos.entities.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;

@Entity //JPA
@Table(name = "users")
@Data // Lombok, generates all getters,  setters for all fields, constructors etc.
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)  //Lombok
@RequiredArgsConstructor //Lombok
@AllArgsConstructor //Lombok
public class User implements UserDetails {

    @Id
    private final String username;

    private final String password;
    private final String fullName;
    private final String street;
    private final String city;
    private final String zipCode;
    private final String phoneNumber;

    private boolean accountValid;
    private boolean credentialsValid;
    private boolean active;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountValid;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsValid;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
