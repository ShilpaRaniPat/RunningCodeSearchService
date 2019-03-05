package com.stackroute.recommendationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.domain.Answer;
import com.stackroute.recommendationservice.domain.Question;
import com.stackroute.recommendationservice.domain.QuestionNode;
import com.stackroute.recommendationservice.domain.UserNode;
import com.stackroute.recommendationservice.repository.UserRepository;
import com.stackroute.recommendationservice.service.RecommendationServiceImpl;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RecommendationControllerTest {
    private static final int QUESTION_ID = 3;
    private static final QuestionNode QUESTION_NODE_ONE = QuestionNode.builder().questionId(QUESTION_ID).upvote(20).build();
    private static final QuestionNode QUESTION_NODE_TWO = QuestionNode.builder().questionId(QUESTION_ID).upvote(0).build();
    private static final Answer ANSWER_REQUESTED = Answer.builder().build();
    private static final Question QUESTION_DOCUMENT_ONE = Question.builder().questionId(3)
            .upvotes(123)
            .timestamp(1551589785355L)
            .answer(Arrays.asList(ANSWER_REQUESTED, ANSWER_REQUESTED, ANSWER_REQUESTED, ANSWER_REQUESTED, ANSWER_REQUESTED))
            .build();
    private static final UserNode USER_NODE = UserNode.builder().username("Sunidhi").reputation(100).build();
    private static final Question QUESTION_DOCUMENT_TWO = Question.builder()
            .answer(Collections.singletonList(ANSWER_REQUESTED)).build();
    private static final String USERNAME = "USERNAME";
    @MockBean
    private RecommendationServiceImpl recommendationService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private RecommendationController recommendationController;
    @Autowired
    private MockMvc mockMvc;

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recommendationController).build();
        ReflectionTestUtils.setField(recommendationController, "questionUpvoteThreshold", 10);
        ReflectionTestUtils.setField(recommendationController, "numberOfAnswersThreshold", 5);
        ReflectionTestUtils.setField(recommendationController, "reputationNeeded", 50);
        ReflectionTestUtils.setField(recommendationController, "timestampThreshold", 5);
        DateTimeUtils.setCurrentMillisFixed(1551693625);
    }

    @After
    public void tearDown() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void testForTrendingQuestionsPresentForRegisteredUser() throws Exception {
        when(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE_ONE));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/trending?username=USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForTrendingQuestionsNotPresentForRegisteredUser() throws Exception {
        when(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.emptyList());
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/trending?username=USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    //

    @Test
    public void testForTrendingQuestionsPresentForGuestUser() throws Exception {
        when(recommendationService.getTrendingQuestionsForGuestUser()).thenReturn(Collections.singletonList(QUESTION_DOCUMENT_ONE));
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/trending")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForTrendingQuestionsNotPresentForGuestUser() throws Exception {
        when(recommendationService.getTrendingQuestionsForGuestUser()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/trending")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void testForUnansweredQuestionsPresentForGuestUser() throws Exception {
        when(recommendationService.getAllUnansweredQuestionsForGuestUser()).thenReturn(Collections.singletonList(QUESTION_DOCUMENT_ONE));
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/unanswered")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForUnansweredQuestionsNotPresentForGuestUser() throws Exception {
        when(recommendationService.getAllUnansweredQuestionsForGuestUser()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/unanswered")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForUpvotesLessThanThreshold() throws Exception {
        when(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE_TWO));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/trending?username=USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testForAnswersLessThanThreshold() throws Exception {
        when(recommendationService.getTrendingQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE_ONE));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_TWO);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/trending?username=USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testForTopicRelatedUsersArePresent() throws Exception {
        when(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).thenReturn(Collections.singletonList(USER_NODE));
        mockMvc.perform(MockMvcRequestBuilders.get("/member/notify?questionId=3")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER_NODE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForTopicRelatedUsersAreNotPresent() throws Exception {
        when(recommendationService.getAllUsersRelatedToQuestion(QUESTION_ID)).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/member/notify?questionId=3")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER_NODE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUnansweredQuestionPresent() throws Exception {
        when(recommendationService.getAllUnansweredQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE_ONE));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/unanswered/USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUnansweredQuestionNotPresent() throws Exception {
        when(recommendationService.getAllUnansweredQuestionsForRegisteredUser(USERNAME)).thenReturn(Collections.emptyList());
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/unanswered/USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAcceptedQuestionsPresent() throws Exception {
        when(recommendationService.getAllAcceptedAnswersOfDomain(USERNAME)).thenReturn(Collections.singletonList(QUESTION_NODE_ONE));
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/acceptedanswers?username=USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAcceptedQuestionsNotPresent() throws Exception {
        when(recommendationService.getAllAcceptedAnswersOfDomain(USERNAME)).thenReturn(Collections.emptyList());
        when(recommendationService.getDocumentByQuestionId(QUESTION_ID)).thenReturn(QUESTION_DOCUMENT_ONE);
        mockMvc.perform(MockMvcRequestBuilders.get("/member/acceptedanswers?username=USERNAME")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION_NODE_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAcceptedQuestionsPresentForGuestUser() throws Exception {
        when(recommendationService.getAllAcceptedAnswersForGuestUser()).thenReturn(Collections.singletonList(QUESTION_DOCUMENT_ONE));
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/acceptedanswers")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(QUESTION_DOCUMENT_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAcceptedQuestionsNotPresentForGuestUser() throws Exception {
        when(recommendationService.getAllAcceptedAnswersForGuestUser()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/acceptedanswers")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(QUESTION_DOCUMENT_ONE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
