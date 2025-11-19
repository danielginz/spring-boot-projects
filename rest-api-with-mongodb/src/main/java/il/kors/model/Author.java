package il.kors.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private String name;
    private String username;
    private String email;
    private String website;
}
