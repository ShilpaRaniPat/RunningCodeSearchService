package com.stackroute.searchservice.repository;

import com.stackroute.searchservice.domain.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends MongoRepository<Topic,String>{

    public List<Topic> findByTopic(String topic);

    @Query(value = "{'_id': 0}", fields="{'questions.question': 1}")
    public List<Topic> findByQuestions(String topic);


}
