package com.stackroute.nlp.service;

import com.stackroute.nlp.domain.Nlp;

import java.util.ArrayList;
import java.util.List;

public interface NlpService {
    public String setquestion(String question) ;
    public ArrayList<String> getLemmitizedWords();
    public ArrayList<String> getRemoveStopWords();
    public String getSentenceWithoutStopWords();
    public List<String> getDomainSpecificTopicName();
    public String getCleanQuestion();
    public ArrayList<Nlp> getPOSWords();
}
