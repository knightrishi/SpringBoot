package net.arnavsingh.journalApp.repository;


import net.arnavsingh.journalApp.entity.JournalEntry;
import net.arnavsingh.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
User findByUsername(String username);


}
