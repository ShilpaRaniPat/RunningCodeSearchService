package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.domain.QuestionNode;
import com.stackroute.recommendationservice.domain.UserNode;
import com.stackroute.recommendationservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationServiceImplTest {
    private static final String USERNAME = "USERNAME";
    private static final UserNode USER_NODE = UserNode.builder().username(USERNAME).reputation(234).build();
    private static final int QUESTION_ID = 10;
    private static final QuestionNode QUESTION_NODE = QuestionNode.builder().questionId(QUESTION_ID)
            .question("").timestamp(213).upvote(12431).downvote(12).build();
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Test
    public void testForTrendingQuestionsPresentInDb() {
        when(userRepository.getAllTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE));
        assertThat(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).containsOnly(QUESTION_NODE);
    }

    @Test
    public void testForTrendingQuestionsNotPresentInDb() {
        when(userRepository.getAllTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).isEmpty();
    }

    @Test
    public void testForUnansweredQuestionsPresentInDb() {
        when(userRepository.findAllUnansweredQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE));
        assertThat(recommendationService.getAllUnansweredQuestionsForRegisteredUser(USERNAME)).containsExactly(QUESTION_NODE);
    }

    @Test
    public void testForUnansweredQuestionsNotPresentInDb() {
        when(userRepository.findAllUnansweredQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getAllUnansweredQuestionsForRegisteredUser(USERNAME)).isEmpty();
    }

    @Test
    public void testForEligibleUsersNotPresentInDb() {
        when(userRepository.findAllUsersRelatedToTopic(QUESTION_ID)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).isEmpty();
    }

    @Test
    public void testForEligibleUsersPresentInDb() {
        when(userRepository.findAllUsersRelatedToTopic(QUESTION_ID)).thenReturn(Collections.singletonList(USER_NODE));
        assertThat(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).containsExactly(USER_NODE);
    }

    @Test
    public void testForAcceptedAnswersPresentInDb() {
        when(userRepository.getAllAcceptedAnswersForDomain(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE));
        assertThat(recommendationService.getAllAcceptedAnswersOfDomain(USERNAME)).containsExactly(QUESTION_NODE);
    }

    @Test
    public void testForAcceptedAnswersNotPresentInDb() {
        when(userRepository.getAllAcceptedAnswersForDomain(USERNAME)).thenReturn(Collections.emptyList());
        assertThat(recommendationService.getAllAcceptedAnswersOfDomain(USERNAME)).isEmpty();
    }


}