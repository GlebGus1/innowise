package validation;

public class ParseValidator {
    private static final String REGEX = "^[^a-zA-Zа-яА-Я]+$";

    public static boolean isValid(String line) {
        return line != null && line.matches(REGEX);
    }
}

