package validation;

import exception.ParsingException;

public class NumberValidator {
    private static final String DOUBLE_NUMBER_FORMAT = "-?\\d+(\\.\\d+)?";

    public void validate(String line) throws ParsingException {
        if (!line.matches(DOUBLE_NUMBER_FORMAT)) {
            throw new ParsingException("Invalid format: " + line);
        }
    }
}
