package com.demo.artist_management_system.Utils;

import com.demo.artist_management_system.Exception.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String arrayToCommaSeparatedString(List<String> values) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!values.isEmpty()) {
            int i = 0;
            for (String value : values) {
                stringBuilder.append(value);
                if (i != values.size()) {
                    stringBuilder.append(",");
                }
                i = i + 1;
            }
            return stringBuilder.toString();
        }
        return null;
    }

    public static List<String> getCommaSeparatedArray(String value, String regex) {
        if (value != null) {
            String[] splittedValues = value.split(regex);

            return Arrays.stream(splittedValues).map(String::trim).collect(Collectors.toList());
        }
        throw new NotFoundException("String Not Found");
    }
}
