package artist_management_system.java.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String arrayToCommaSeparatedString(List<String> values) {
        StringBuilder sb = new StringBuilder();
        if(values.isEmpty()) {
            return null;
        }

        int i = 0;
        for(String value : values) {
            sb.append(value);
            if(i != values.size()) {
                sb.append(",");
            }
            i = i +1;
        }
        return sb.toString();
    }

    public static List<String> getCommaSeparatedArray(String value, String regex) {
        if(value == null || value.isEmpty()) {
            throw new NullPointerException("value is null or empty");
        }
        String[] splitValue = value.split(regex);
        return Arrays.stream(splitValue).map(String::trim).collect(Collectors.toList());
    }
}
