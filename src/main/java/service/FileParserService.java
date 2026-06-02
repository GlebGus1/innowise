package service;

import exception.InvalidArgumentException;
import exception.ParsingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileParserService {

    public Double[] readNumbersFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        List<Double> numbers = Files.readAllLines(path).stream()
                .filter(line -> !line.isBlank())
                .flatMap(line -> Arrays.stream(line.split("[,\\s]+")))
                .filter(s -> !s.isEmpty())
                .map(this::tryParseDouble)
                .toList();

        return numbers.toArray(new Double[0]);
    }

    private Double tryParseDouble(String s) {
        if (!s.matches("-?\\d+(\\.\\d+)?")) {
            throw new InvalidArgumentException("Invalid value skipped (contains letters or format)");
        }

        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new ParsingException("Parsing error");
        }
    }
}
