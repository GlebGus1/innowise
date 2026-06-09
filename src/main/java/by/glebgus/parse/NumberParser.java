package parse;

import exception.ParsingException;
import validation.NumberValidator;

public class NumberParser {
    private final NumberValidator validator;

    public NumberParser(NumberValidator validator) {
        this.validator = validator;
    }

    public Double parse(String s) throws ParsingException {
        validator.validate(s);
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new ParsingException("Parsing error for value: " + s);
        }
    }
}
