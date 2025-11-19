package il.kors.controller;

import il.kors.client.JsonplaceholderClient;
import il.kors.model.Author;
import il.kors.model.Post;
import il.kors.repository.CustomerRepository;
import il.kors.repository.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostRepository repository;
    private final JsonplaceholderClient client;
    private final CustomerRepository customerRepository;
    private final List<Author> authors;


    public PostController(PostRepository repository, JsonplaceholderClient client, CustomerRepository customerRepository) {
        this.repository = repository;
        this.client = client;
        this.authors = client.getAllAuthors();
        this.customerRepository = customerRepository;
    }

    @PutMapping("/update/title/{id}")
    public void updateTitle(@PathVariable String id,
                            @RequestBody String title) {
        customerRepository.updateTitle(new ObjectId(id), title);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<Post> pageablePosts(@PathVariable int pageNumber, @PathVariable int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> page = repository.findAll(pageable);
        return page.getContent();
    }

    @GetMapping("/author/{username}")
    public List<Post> findByAuthorsUserName(@PathVariable String username) {
        return repository.findByAuthorsUserName(username);
    }

    /*@GetMapping("/title/{title}")
     public Post findByTitle(@PathVariable String title) {
        return repository.findByTitle(title);
    }*/

    @GetMapping("/title/{title}")
    public List<Post> findByTitle(@PathVariable String title) {
        return repository.findAllByTitle(title);
    }

    @GetMapping("/id/{id}")
    public Optional<Post> findById(@PathVariable String id) {
        return repository.findById(new ObjectId(id));
    }

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post) {
        return repository.save(post);
    }

    @PutMapping("/update/body/{id}")
    public Post updateBody(@PathVariable String id, @RequestBody String body) {
        Post post = repository.findById(new ObjectId(id)).orElse(null);
        if(post == null) {
            return null;
        }
        post.setBody(body);
        repository.save(post);
        return post;
    }

    @DeleteMapping("/delete")
    public void removePost(@RequestBody Post post) {
        repository.delete(post);
    }

    @GetMapping("/")
    public List<Post> findAll() {
        return repository.findAll();
    }

    @GetMapping("add-posts-in-repo")
    public List<Post> init() {
        List<Post> posts = client.getAllPosts();
        for(Post post : posts) {
            post.setAuthors(
                    randdomAuthors()
            );
        }
        repository.saveAll(posts);
        return posts;
    }

    @DeleteMapping("/delete-all")
    public void deleteAll() {
        repository.deleteAll();;
    }

    private List<Author> randdomAuthors() {
        Random random = new Random();
        return authors.stream()
                .skip(random.nextInt(authors.size()))
                .limit(random.nextInt(1,4))
                .toList();
    }
}
