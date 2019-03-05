package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AnswerRepository extends Neo4jRepository<Answer, String> {

    //method to get ANSWERS//
    @Query("MATCH (m:Answer) RETURN m")
    Collection<Answer> getAllAnswers();


    //method to create relationship ANSWERED between userDTO and answerDTO//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerString={answerstring} create (q)-[r:answered]->(t) return q")
    User userAnsweredAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);


    //    //method to create relationship ACCEPTED between userDTO and answerDTO//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerString={answerstring} create (q)-[r:accepted]->(t)")
    User userAcceptedAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);


    //method to create relationship UPVOTED between userDTO and answerDTO//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerString={answerstring} create (q)-[r:upvoted]->(t)")
    User userUpvotedAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);


    //method to create relationship DOWNVOTED between userDTO and answerDTO//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerString={answerstring} create (q)-[r:downvoted]->(t)")
    User userDownvotedAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);
}


