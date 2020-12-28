package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Picture {

    private String name;
    private String type;
    private String source;
    private Long size;

    // This is to construct routing key for topic exchange
    @Override
    public String toString() {
        return source + "." + (size > 4000 ? "large" : "small") + "." + type;
    }
}
