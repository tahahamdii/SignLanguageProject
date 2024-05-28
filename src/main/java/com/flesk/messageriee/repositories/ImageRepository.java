package com.flesk.messageriee.repositories;

import com.flesk.messageriee.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {

    List<Image> findByOrderById();
}
