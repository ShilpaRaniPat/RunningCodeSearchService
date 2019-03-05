package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.domain.Question;
import com.stackroute.recommendationservice.domain.QuestionNode;
import com.stackroute.recommendationservice.domain.UserNode;

import java.util.List;

public interface RecommendationService {

    List<QuestionNode> getAllUnansweredQuestionsForRegisteredUser(String userName);

    List<Question> getAllUnansweredQuestionsForGuestUser();

    Question getDocumentByQuestionId(long questionId);

    List<UserNode> getAllUsersRelatedToQuestion(long questionID);

    List<QuestionNode> getTrendingQuestionsForRegisteredUser(String username);

    List<Question> getTrendingQuestionsForGuestUser();

    List<QuestionNode> getAllAcceptedAnswersOfDomain(String username);

    List<Question> getAllAcceptedAnswersForGuestUser();
}

