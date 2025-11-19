package il.kors.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Data
@Builder
//In standart ORM we use @Entity annotation, for mongodb we use @Document
@Document(collection = "posts")
public class Post {
    @Id
    private ObjectId _id;
    private List<Author> authors;
    private String title;
    private String body;
}
