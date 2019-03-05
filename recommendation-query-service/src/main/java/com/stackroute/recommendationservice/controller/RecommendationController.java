package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.domain.Question;
import com.stackroute.recommendationservice.domain.UserNode;
import com.stackroute.recommendationservice.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@CrossOrigin("*")
@PropertySource(value = "classpath:trending-criteria.yml")
public class RecommendationController {
    @Value("${trending-timestampThreshold}")
    private long timestampThreshold;
    @Value("${trending-upvote-threshold}")
    private int questionUpvoteThreshold;
    @Value("${trending-number-of-answers-for-the-question}")
    private int numberOfAnswersThreshold;
    @Value("${reputation-to-answer-the-question}")
    private int reputationNeeded;

    private RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * @param username Username of the registered user
     * @return Trending questions for the logged in user
     */
    @GetMapping("/member/trending")
    public ResponseEntity<List<Question>> getTrendingQuestionsForRegisteredUser(@RequestParam String username) {
        ResponseEntity<List<Question>> responseEntity;
        List<Question> questionDocuments = new ArrayList<>();
        try {
            List<Question> trendingDocuments = recommendationService.getTrendingQuestionsForRegisteredUser(username)
                    .stream()
                    .peek(question -> log.info("Question node is {}", question))
                    .filter(question -> question.getUpvote() >= questionUpvoteThreshold && Math.abs(DateTime.now().getMillis() - question.getTimestamp()) <= timestampThreshold)
                    .map(question -> recommendationService.getDocumentByQuestionId(question.getQuestionId()))
                    .filter(questionRequested -> questionRequested.getAnswer().size() >= numberOfAnswersThreshold)
                    .peek(questionRequested -> log.info("Question document is {}", questionRequested))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(trendingDocuments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @return Trending questions for the guest user
     */
    @GetMapping("/guest/trending")
    public ResponseEntity<List<Question>> getTrendingQuestionsForGuestUser() {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> questionDocuments = recommendationService.getTrendingQuestionsForGuestUser()
                    .stream()
                    .filter(document -> document.getUpvotes() >= questionUpvoteThreshold
                            && document.getAnswer().size() >= numberOfAnswersThreshold
                            && Math.abs(DateTime.now().getMillis() - document.getTimestamp()) <= timestampThreshold)
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(questionDocuments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @param questionId QuestionNode Id of the posted question
     * @return List of users who will be notified
     */

    @GetMapping("/member/notify")
    public ResponseEntity<List<UserNode>> getAllUsersToBeNotified(@RequestParam long questionId) {
        ResponseEntity<List<UserNode>> responseEntity;
        List<UserNode> userNodes;
        try {
            userNodes = recommendationService.getAllUsersRelatedToQuestion(questionId)
                    .stream()
                    .filter(user -> user.getReputation() >= reputationNeeded)
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(userNodes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @param username Username of the registered user
     * @return List of unanswered questions for the logged in user
     */
    @GetMapping("/member/unanswered/{username}")
    public ResponseEntity<List<Question>> getAllUnansweredQuestionsForRegisteredUser(@PathVariable String username) {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> unansweredQuestions = recommendationService.getAllUnansweredQuestionsForRegisteredUser(username)
                    .stream()
                    .map(question -> recommendationService.getDocumentByQuestionId(question.getQuestionId()))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(unansweredQuestions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @return List of unanswered questions for guest user
     */
    @GetMapping("/guest/unanswered")
    public ResponseEntity<List<Question>> getAllUnansweredQuestionsForGuestUser() {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> question = recommendationService.getAllUnansweredQuestionsForGuestUser()
                    .stream()
                    .sorted(Comparator.comparing(Question::getTimestamp))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * @param username Username of the loggedin user
     * @return List of Accepted answers of the domain which user follows
     */
    @GetMapping("/member/acceptedanswers")
    public ResponseEntity<List<Question>> getAllAcceptedAnswersOfDomain(@RequestParam String username) {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> acceptedAnswers = recommendationService.getAllAcceptedAnswersOfDomain(username)
                    .stream()
                    .map(question -> recommendationService.getDocumentByQuestionId(question.getQuestionId()))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(acceptedAnswers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    /**
     * @return List of accepted answers to guest users
     */
    @GetMapping("/guest/acceptedanswers")
    public ResponseEntity<List<Question>> getAcceptedAnswersForGuest() {
        ResponseEntity<List<Question>> responseEntity;
        try {
            List<Question> question = recommendationService.getAllAcceptedAnswersForGuestUser()
                    .stream()
                    .sorted(Comparator.comparing(Question::getTimestamp))
                    .collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}

