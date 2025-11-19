package il.kors.repository;

import il.kors.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, ObjectId> {
    Post findByTitle(String title);

    @Query("title: {$regex: '?0', $options: 'i'}}")//due to I have only one parameter, then its index will be 0; 'i' - never mind capital or small letter
    List<Post> findAllByTitle(String title);

    @Query("{authors: {$elemMatch: {username: '?0'}}}")
    List<Post> findByAuthorsUserName(String username);
}
