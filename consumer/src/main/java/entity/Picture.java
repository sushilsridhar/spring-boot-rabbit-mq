package entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@NoArgsConstructor
public class Picture {

    private String name;
    private String type;
    private String source;
    private Long size;
}
