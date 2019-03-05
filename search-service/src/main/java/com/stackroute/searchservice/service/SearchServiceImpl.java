package com.stackroute.searchservice.service;

import com.stackroute.searchservice.exceptions.ConceptNotFoundException;
import com.stackroute.searchservice.domain.*;
//import com.stackroute.searchservice.model.Answer;
import com.stackroute.searchservice.model.Comment;
import com.stackroute.searchservice.model.QuestionDTO;
import com.stackroute.searchservice.repository.SearchRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    //Overrided method for saving searched questions//
    @Override
    public void saveTopic(Topic topic) {
        searchRepository.save(topic);
    }

    //Overrided method for getAllTopics//
    @Override
    public List<Topic> getAllTopics() {
        return searchRepository.findAll();
    }

    //Overrided method for getQuestionsByTopic//
    @Override
    public List<Topic> getQuestionsByTopic(String topic) {
        return searchRepository.findByTopic(topic);
    }

    //Overrided method for getQuestion//

    @Override
    public List<Topic> getQuestion(List<String> topic) throws ConceptNotFoundException {
        for (String topicName : topic
        ) {


            if (!searchRepository.existsById(topicName)) {
                throw new ConceptNotFoundException("Result not found");
            }
            return searchRepository.findByTopic(topicName);
        }
        return  null;
    }

    @Override
    public List<com.stackroute.searchservice.domain.Question> questionOfTopic(String topic, String question) throws ConceptNotFoundException {

        if (!searchRepository.existsById(topic)) {
            throw new ConceptNotFoundException("Result not found");
        }
        Topic topics = searchRepository.findById(topic).get();
        List<com.stackroute.searchservice.domain.Question> questionLists = topics.getQuestions();
        List<com.stackroute.searchservice.domain.Question> questions = new ArrayList<>();
        for (com.stackroute.searchservice.domain.Question questionList : questionLists) {
            if (questionList.getQuestion().contains(question.toString())) {
                questions.add(questionList);
            }
        }

        return questions;
    }


    @Override
    public List<com.stackroute.searchservice.domain.Question> questionOfPost(QuestionDTO questionDTO) {



        List<String> topicList = questionDTO.getTopics();
        topicList.replaceAll(String::toLowerCase);

        questionDTO.getTopics().forEach(topic -> {
            Question question = new Question();
            question.setQuestion(questionDTO.getQuestion());
            question.setDescription(questionDTO.getDescription());
            question.setUpvotes(questionDTO.getUpvotes());
            question.setTimestamp(questionDTO.getTimestamp());
            question.setDownvote(questionDTO.getDownvotes());


            List<Comment> commentList = questionDTO.getComment();
            List<Comments> commentsList = new ArrayList<>();


            for (Comment comment : commentList) {
                List<Replies> replies = new ArrayList<>();
                for (com.stackroute.searchservice.model.Replies reply : comment.getReplies()) {
                    User user = new User(reply.getUser().getEmailaddress(), reply.getUser().getFirstname(), reply.getUser().getImageurl());
                    Replies replies1 = new Replies(reply.getReply(), reply.getLikes(), reply.getTimestamp(), user);
                    replies.add(replies1);
                }
                User user = new User(questionDTO.getUser().getEmailaddress(), questionDTO.getUser().getFirstname(), questionDTO.getUser().getImageurl());

                Comments comments = new Comments(comment.getComment(), comment.getTimestamp(), comment.getLikes(), replies, user);
                commentsList.add(comments);
            }

            question.setComments(commentsList);

            List<Answer> answerList = new ArrayList<>();
            for (com.stackroute.searchservice.model.Answer answer : questionDTO.getAnswer()) {
                List<Comments> answerCommentsList = new ArrayList<>();
                for (Comment comment : commentList) {
                    List<Replies> replies = new ArrayList<>();
                    for (com.stackroute.searchservice.model.Replies reply : comment.getReplies()) {
                        User user = new User(reply.getUser().getEmailaddress(), reply.getUser().getFirstname(), reply.getUser().getImageurl());
                        Replies replies1 = new Replies(reply.getReply(), reply.getLikes(), reply.getTimestamp(), user);
                        replies.add(replies1);
                    }


                    User user = new User(questionDTO.getUser().getEmailaddress(), questionDTO.getUser().getFirstname(), questionDTO.getUser().getImageurl());


                    Comments comments = new Comments(comment.getComment(), comment.getTimestamp(), comment.getLikes(), replies, user);
                    answerCommentsList.add(comments);
                }

                User userAnswer = new User(answer.getUser().getEmailaddress(), answer.getUser().getFirstname(), answer.getUser().getImageurl());
                Answer answerDomain = new Answer(answer.getAnswer(),answer.isAccepted(),answerCommentsList,answer.getUpvotes(),answer.getViews(),answer.getTimestamp(),userAnswer);
            answerList.add(answerDomain);
            }
            question.setAnswers(answerList);
            User user = new User(questionDTO.getUser().getEmailaddress(), questionDTO.getUser().getFirstname(), questionDTO.getUser().getImageurl());
            question.setUser(user);


            Topic topicDoc = searchRepository.findById(topic).orElse(new Topic());
            topicDoc.getQuestions().forEach(questionFromDb -> {
                if (questionFromDb.getQuestion().equalsIgnoreCase(question.getQuestion())) {
                    System.out.println("print true here");
                    questionFromDb.setDownvote(question.getDownvote());
                    questionFromDb.setTimestamp(question.getTimestamp());
                    questionFromDb.setComments(question.getComments());
                    questionFromDb.setAnswers(question.getAnswers());
                    questionFromDb.setUpvotes(question.getUpvotes());
                    questionFromDb.setDescription(question.getDescription());
                    questionFromDb.setQuestion(question.getQuestion());
                    questionFromDb.setUser(question.getUser());
                    System.out.println("daata before rabbit ");
                    System.out.println("data from rabbit " + topicDoc);
                    System.out.println("data after rabbit");
                    searchRepository.save(topicDoc);

                }

//                else{
//                    System.out.println("print false here");
//                    Question questionNew=new Question();
//                    questionNew.setQuestion(questionDTO.getQuestion());
//                    questionNew.setDescription(questionDTO.getDescription());
//                    questionNew.setUpvotes(questionDTO.getUpvotes());
//                    questionNew.setTimestamp(questionDTO.getTimestamp());
//                    questionNew.setDownvote(questionDTO.getDownvotes());
//
//
//
//                    List<Comment> commentListElsePart = questionDTO.getComment();
//                    List<Comments> commentsListElsePart = new ArrayList<>();
//
//
//
//                    for (Comment comment : commentListElsePart){
//                        List<Replies> replies = new ArrayList<>();
//                        for (com.stackroute.model.Replies reply: comment.getReplies()) {
//                            User userElsePart = new User(reply.getUser().getEmailaddress(),reply.getUser().getFirstname(),reply.getUser().getImageurl());
//                            Replies replies1 = new Replies(reply.getReply(),(int)reply.getLikes(),reply.getTimestamp(),userElsePart);
//                            replies.add(replies1);
//                        }
//                        User userElsePart = new User(questionDTO.getUser().getEmailaddress(),questionDTO.getUser().getFirstname(),questionDTO.getUser().getImageurl());
//
//                        Comments comments = new Comments(comment.getComment(),comment.getTimestamp(),(int)comment.getLikes(),replies,userElsePart);
//                        commentsListElsePart.add(comments);
//                    }
//
//                    questionNew.setComments(commentsList);
//
//
//                    List<Answer> answerListElsePart = new ArrayList<>();
//                    for (com.stackroute.model.Answer answer:questionDTO.getAnswer()) {
//                        List<Comments> answerCommentsList = new ArrayList<>();
//                        for (Comment comment : commentList){
//                            List<Replies> replies = new ArrayList<>();
//                            for (com.stackroute.model.Replies reply: comment.getReplies()) {
//                                User userElsePart = new User(reply.getUser().getEmailaddress(),reply.getUser().getFirstname(),reply.getUser().getImageurl());
//                                Replies replies1 = new Replies(reply.getReply(),(int)reply.getLikes(),reply.getTimestamp(),userElsePart);
//                                replies.add(replies1);
//                            }
//
//
//                            User userElsePart = new User(questionDTO.getUser().getEmailaddress(),questionDTO.getUser().getFirstname(),questionDTO.getUser().getImageurl());
//
//
//                            Comments comments = new Comments(comment.getComment(),comment.getTimestamp(),(int)comment.getLikes(),replies,userElsePart);
//                            answerCommentsList.add(comments);
//                        }
//
//                        User userAnswer  = new User(answer.getUser().getEmailaddress(),answer.getUser().getFirstname(),answer.getUser().getImageurl());
//                        Answer answerDomain = new Answer(answer.getAnswer(),answer.isAccepted(),answerCommentsList,answer.getUpvotes(),answer.getViews(),answer.getTimestamp(),userAnswer);
//                        answerListElsePart.add(answerDomain);
//                    }
//                    questionNew.setAnswers(answerList);
//                    User userElsePart = new User(questionDTO.getUser().getEmailaddress(),questionDTO.getUser().getFirstname(),questionDTO.getUser().getImageurl());
//                    questionNew.setUser(userElsePart);
//
//
//
//
//                 topicDoc.getQuestions().add(questionNew);
//                 System.out.println("some results"+topicDoc);
//                 searchRepository.save(topicDoc);
//
//                }
            });
        });
        return null;
    }
}
