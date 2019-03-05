package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User, String> {

    //method to get USER//
    @Query("MATCH (m:User) RETURN m")
    Collection<User> getAllUsers();


    //method to get USER using REPUTATION//
    @Query("match (m:User) where m.reputation={reputation} return m")
    User getByUser(@Param("reputation") int reputation);


    //method to create relationship FOLLOWS between userDTO and topic//
    @Query("match (q:User),(t:parents) where q.userName={username} and t.name={name} create (q)-[r:follows]->(t)")
    User userFollowsTopicRelationship(@Param("username") String userName, @Param("name") String name);
}
