package med.voll.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public class StringUtil {

    public static boolean isTextoPreenchido(String texto) {
        return Objects.nonNull(texto) && !texto.isBlank();
    }

    public static String criptografar(String texto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(texto);
    }
}