package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.Question;
import com.stackroute.recommendationcommandservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends Neo4jRepository<Question, Integer> {


    //method to get QUESTIONS//
    @Query("MATCH (m:Question) RETURN m")
    Collection<Question> getAllQuestions();

    //method to create relationship VIEWED between userDTO and question//
    @Query("match (q:User),(t:Question) where q.userName={username} and t.questionId={questionid} create (q)-[r:viewed]->(t)")
    User userViewedQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    //method to create relationship ANSWER_OF between answerDTO and question//
    @Query("match (q:Answer),(t:Question) where q.answerString={answerstring} and t.questionId={questionid} create (q)-[r:answer_of]->(t)")
    Answer answerIsAnswerOfQuestionRelationship(@Param("answerstring") String answerString, @Param("questionid") int questionId);


    //method to create relationship ASKED between userDTO and question//
    @Query("match (q:User),(t:Question) where q.userName={username} and t.questionId={questionid} create (q)-[r:asked]->(t)")
    User userAskedQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    //method to create relationship UPVOTE between userDTO and question//
    @Query("match (q:User),(t:Question) where q.userName={username} and t.questionId={questionid} create (q)-[r:upvoted]->(t)")
    User userUpvoteQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    //method to create relationship DOWNVOTE between userDTO and question//
    @Query("match (q:User),(t:Question) where q.userName={username} and t.questionId={questionid} create (q)-[r:downvoted]->(t)")
    User userDownvoteQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    ////method to create relationship BELONGS between question and topic//
    @Query("match (q:Question),(t:parents) where q.questionId={questionid} and t.name={name} create (q)-[r:question_of_topic]->(t)")
    Question questionBelongsTopicRelationship(@Param("questionid") int questionId, @Param("name") List<String> name);



}
