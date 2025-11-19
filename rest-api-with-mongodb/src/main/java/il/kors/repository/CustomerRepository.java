package il.kors.repository;

import org.bson.types.ObjectId;

public interface CustomerRepository {
    void updateTitle(ObjectId id, String newTitle);
}
