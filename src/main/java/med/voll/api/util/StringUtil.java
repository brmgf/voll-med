package med.voll.api.util;

import java.util.Objects;

public class StringUtil {

    public static boolean isTextoPreenchido(String texto) {
        return Objects.nonNull(texto) && !texto.isBlank();
    }
}