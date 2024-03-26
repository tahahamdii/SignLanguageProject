package com.flesk.messageriee.repositories;
import com.flesk.messageriee.models.Chanel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChanelRepository extends MongoRepository<Chanel, String> {

}
