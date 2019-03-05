
package com.stackroute.searchservice.service;

import com.stackroute.searchservice.exceptions.ConceptNotFoundException;
import com.stackroute.searchservice.domain.Question;
import com.stackroute.searchservice.domain.Topic;
import com.stackroute.searchservice.model.QuestionDTO;

import java.util.List;

public interface SearchService {
    public void saveTopic(Topic topic) ;
    //    public Question saveQuestion(Question question);
    public List<Topic> getAllTopics() throws ConceptNotFoundException;
    public List<Topic> getQuestionsByTopic(String topic);
    public List<Topic> getQuestion(List<String> topic) throws ConceptNotFoundException;
    public List<Question> questionOfTopic(String topic,String question) throws ConceptNotFoundException;
    public List<com.stackroute.searchservice.domain.Question> questionOfPost(QuestionDTO question);
//    public QuestionDTO savequestionDTO(QuestionDTO questionDTO);
//    public List<Question> questionSorted(String topic,String question);
}



