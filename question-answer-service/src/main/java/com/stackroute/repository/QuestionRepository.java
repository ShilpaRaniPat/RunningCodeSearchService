package com.stackroute.repository;

import com.stackroute.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, Integer> {
    boolean existsByQuestion(String question);

    Question findByQuestionId(int questionId);
}
