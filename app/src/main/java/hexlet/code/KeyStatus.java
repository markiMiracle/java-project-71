package hexlet.code;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
