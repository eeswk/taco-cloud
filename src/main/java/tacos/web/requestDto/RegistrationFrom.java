package tacos.web.requestDto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.auth.entity.User;

@Data
public class RegistrationFrom {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;


    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullname, state, city, state, zip, phone);
    }
}
