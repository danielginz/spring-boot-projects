package il.kors.client;

import il.kors.model.Author;
import il.kors.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface JsonplaceholderClient {
    @GetMapping("/posts")
    List<Post> getAllPosts();

    @GetMapping("/users")
    List<Author> getAllAuthors();
}
