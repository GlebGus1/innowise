package service;

import exception.ParsingException;
import parse.NumberParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

    public class NumberArrayReaderService {
        private static final String SPLIT_REGEX = "[,\\s]+";
        private final FileReaderService fileReader;
        private final NumberParser numberParser;

        public NumberArrayReaderService(FileReaderService fileReader, NumberParser numberParser) {
            this.fileReader = fileReader;
            this.numberParser = numberParser;
        }

        public Double[] readNumbersFromFile(String filePath) throws IOException {
            List<Double> numbers = fileReader.readLines(filePath).stream()
                    .filter(line -> !line.isBlank())
                    .flatMap(line -> Arrays.stream(line.split(SPLIT_REGEX)))
                    .filter(s -> !s.isEmpty())
                    .map(s -> {
                        try {
                            return numberParser.parse(s);
                        } catch (ParsingException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();

            return numbers.toArray(new Double[0]);
        }
}
