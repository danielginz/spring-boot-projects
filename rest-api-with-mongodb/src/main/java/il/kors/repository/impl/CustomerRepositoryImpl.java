package il.kors.repository.impl;

import il.kors.model.Post;
import il.kors.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final MongoTemplate template;//MongoTemplate - class that implements Mongodb operations

    @Override
    public void updateTitle(ObjectId id, String newTitle) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("title", newTitle);
        template.updateFirst(query, update, Post.class);
    }
}
