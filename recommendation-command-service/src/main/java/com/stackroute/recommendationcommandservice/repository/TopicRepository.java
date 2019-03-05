package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.Topic;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface TopicRepository extends Neo4jRepository<Topic, Integer> {



}





