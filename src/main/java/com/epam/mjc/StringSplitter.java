package com.epam.mjc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String regex = delimiters.stream().collect(Collectors.joining(")|(", "((", ")|(\\s))+"));
        return Arrays.stream(source.split(regex))
                .filter(token -> !token.isEmpty())
                .collect(Collectors.toList());
    }
}
