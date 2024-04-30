package hexlet.code;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyStatus {
    private String keyName;
    private String statusOfKey;
    private Object pastValue;
    private Object currentValue;

    public static String isComplexValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value.getClass() == Arrays.class || value.getClass() == ArrayList.class || value.getClass() == Object.class
                || value.getClass() == LinkedHashMap.class) {
            return "[complex value]";
        } else if (value.getClass() == String.class) {
            return "'" + value + "'";
        } else {
            return "" + value;
        }
    }
}
