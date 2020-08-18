package tacos.security;

        import org.springframework.security.crypto.password.PasswordEncoder;

public class NoEncodingPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPwd) {
        return charSequence.toString().equals(encodedPwd);
    }
}