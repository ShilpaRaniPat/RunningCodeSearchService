//package com.stackroute.recommendationcommandservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.recommendationcommandservice.model.Answer;
//import com.stackroute.recommendationcommandservice.model.Question;
//import com.stackroute.recommendationcommandservice.model.User;
//import com.stackroute.recommendationcommandservice.repository.AnswerRepository;
//import com.stackroute.recommendationcommandservice.repository.QuestionRepository;
//import com.stackroute.recommendationcommandservice.repository.UserRepository;
//import com.stackroute.recommendationcommandservice.service.RecommendationCommandServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Collections;
//
//import static org.mockito.Mockito.when;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class RecommendationControllerTest {
//
//    private static final User USER = User.builder().userName("kate").reputation(1).build();
//    private static final Question QUESTION = Question.builder().questionId(12).questionString("what is java").timestamp(34544).upVote(32).downVote(1).build();
//    private static final Answer ANSWER = Answer.builder().answerId(21).answerString("Dont do it").build();
//
//    @MockBean
//    private RecommendationCommandServiceImpl recommendationCommandService;
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private AnswerRepository answerRepository;
//
//    @MockBean
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//    @InjectMocks
//    private RecommendationController recommendationController;
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void addUser() throws Exception {
//        when(userRepository.save(USER)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.post("/userDTO")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void addQuestion() throws Exception {
//        when(questionRepository.save(QUESTION)).thenReturn(QUESTION);
//        mockMvc.perform(MockMvcRequestBuilders.post("/question")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//
//    @Test
//    public void addAnswer() throws Exception {
//        when(answerRepository.save(ANSWER)).thenReturn(ANSWER);
//        mockMvc.perform(MockMvcRequestBuilders.post("/answerDTO")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(ANSWER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//
//    @Test
//    public void getAllUsers() throws Exception {
//        when(userRepository.getAllUsers()).thenReturn(Collections.singletonList(USER));
//        mockMvc.perform(MockMvcRequestBuilders.get("/users")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getAllQuestions() throws Exception {
//        when(questionRepository.getAllQuestions()).thenReturn(Collections.singletonList(QUESTION));
//        mockMvc.perform(MockMvcRequestBuilders.get("/questions")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void getAllAnswers() throws Exception {
//        when(answerRepository.getAllAnswers()).thenReturn(Collections.singletonList(ANSWER));
//        mockMvc.perform(MockMvcRequestBuilders.get("/answers")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(ANSWER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void getUserByReputation() throws Exception {
//        when(userRepository.getByUser(1)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/userDTO/{reputation}", 1)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void createRelationshipfollows() throws Exception {
//        when(userRepository.userFollowsTopicRelationship("meghana", "testing")).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/follows/{userName}/{name}", "meghana", "testing")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void createRelationshipbelongs() throws Exception {
//        when(questionRepository.questionBelongsTopicRelationship(12, "pipes")).thenReturn(QUESTION);
//        mockMvc.perform(MockMvcRequestBuilders.get("/belongs/{questionId}/{name}", "12", "pipes")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//
//    @Test
//    public void createRelationshipanswered() throws Exception {
//        when(answerRepository.userAnsweredAnswerRelationship("srinidhi", 201)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/answered/{userName}/{answerId}", "srinidhi", 201)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void createRelationshipviewed() throws Exception {
//        when(questionRepository.userViewedQuestionRelationship("anirudh", 101)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/viewed/{userName}/{questionId}", "anirudh", 101)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void createRelationshipanswerof() throws Exception {
//        when(questionRepository.answerIsAnswerOfQuestionRelationship(202, 101)).thenReturn(ANSWER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/answerof/{answerId}/{questionId}", 201, 101)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(ANSWER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//
//    @Test
//    public void createRelationshipasked() throws Exception {
//        when(questionRepository.userAskedQuestionRelationship("kiran", 101)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/asked/{userName}/{questionId}", "kiran", 101)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void createRelationshipaccepted() throws Exception {
//        when(answerRepository.userAcceptedAnswerRelationship("gagana", 201)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/accepted/{userName}/{answerId}", "gagana", 201)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void createRelationshipupvoted() throws Exception {
//        when(answerRepository.userUpvotedAnswerRelationship("varun", 201)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/upvoted/{userName}/{answerId}", "varun", 201)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void createRelationshipdownvoted() throws Exception {
//        when(answerRepository.userDownvotedAnswerRelationship("harsha Bean", 222)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/downvoted/{userName}/{answerId}", "varun", 201)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void createRelationshipUpvoted() throws Exception {
//        when(questionRepository.userUpvoteQuestionRelationship("easwar", 2696)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/Upvoted/{userName}/{questionId}", "easwar", 2696)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void createRelationshipDownvoted() throws Exception {
//        when(questionRepository.userDownvoteQuestionRelationship("siddharth", 102)).thenReturn(USER);
//        mockMvc.perform(MockMvcRequestBuilders.get("/Downvoted/{userName}/{questionId}", "siddharth", 102)
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//}