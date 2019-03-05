package com.stackroute.nlp.service;

import com.stackroute.nlp.domain.Nlp;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NlpServiceImpl implements NlpService{

    @Value("${jsb.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsb.rabbitmq.routingkey}")
    private String routingKey;

    private static final Logger log = LoggerFactory.getLogger(NlpServiceImpl.class);
    String question;
    String[] stopwords = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "could", "he'd", "above", "below", "be", "what", "in", "on", "above",
            "is", "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "could", "he'd", "!", "@", "#", "$", "%", "^", "&", "*", "()", ".", "?",
            "he'll", "he's", "here's", "how's", "ought", "she'd", "she'll", "that's", "there's", "they'd",
            "they'll", "they're", "they've", "we'd", "we'll", "we're", "we've", "what's", "when's", "where's",
            "who's", "why's", "would", "i'd", "i'll", "i'm", "i've", "you", "you're", "you've", "you'll",
            "you'd", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she",
            "she's", "her", "hers", "herself", "it", "it's", "its", "itself", "they", "them", "their",
            "theirs", "themselves", "who", "whom", "this", "that", "that'll", "these", "what", "why", "which",};


    ArrayList<String> domainSpecificTopics = new ArrayList<>(Arrays.asList("Angular","pipes","Fundamentals and Architecture","Navigation","server Side","Using Promises","Http Client","Configuring Routes","Routing","Custom pipes","using pipes","Data Binding","Templates"));
    @Autowired
    private AmqpTemplate amqpTemplate;

    public NlpServiceImpl() {

    }


    public String setquestion(String question) {
        this.question = question;
        getDomainSpecificTopicName();
        return question;
    }

    @Override
    public String getCleanQuestion() {
        String inputsentence = this.question;
        // Data Cleaning by removing extra spaces.
        inputsentence = inputsentence.trim();
        inputsentence = inputsentence.replaceAll("\\s+", " ");
        inputsentence = inputsentence.replaceAll("\\t", " ");
        return inputsentence.trim();
    }




    //removal of stop words from the lemmitizedWords
    @Override
    public ArrayList<String> getLemmitizedWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "lemma");
        //predefined properties of Nlp Stanford
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        // This annotations object gives the special meaning to the
        // string we used in propeties.put() method
        Annotation annotations = new Annotation(getCleanQuestion());
        // pipeline.annotate(annotations)  provies the annotation to those particular objects.
        pipeline.annotate(annotations);
        // wordsList contains list of lemmetized words
        ArrayList<String> lemmaWords = new ArrayList<>();
        ArrayList<String> originalWords = new ArrayList<>();
        List<CoreMap> wordsList = annotations.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap words : wordsList) {
            for (CoreLabel word : words.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmaWords.add(word.lemma());
                originalWords.add(word.originalText());
            }
        }
        return lemmaWords;
    }

    //removal of stop words from the sentence
    @Override
    public ArrayList<String> getRemoveStopWords() {
        ArrayList<String> removeStopwords = getLemmitizedWords();
        for (int i = 0; i < stopwords.length; i++) {
            removeStopwords.remove(stopwords[i]);
        }
        return removeStopwords;
    }
    //Removal of stopwords from the sentence
    @Override
    public String getSentenceWithoutStopWords() {
        ArrayList<String> removeStopwords = getRemoveStopWords();
        StringBuffer sentenceWithoutStopWords = new StringBuffer();
        for (int i = 0; i < removeStopwords.size(); i++) {
            sentenceWithoutStopWords.append(removeStopwords.get(i) + " ");
        }
        return sentenceWithoutStopWords.toString().trim();
    }
    @Override
    //List of parts of speech words
    public ArrayList<Nlp> getPOSWords() {
        Properties properties = new Properties();
        properties.setProperty("annotator", "pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument coreDocument = new CoreDocument(getSentenceWithoutStopWords());
        pipeline.annotate(coreDocument);
        List<CoreLabel> coreLabelsList = coreDocument.tokens();
        ArrayList<Nlp> wordsWithPOSTag = new ArrayList<>();
        for (CoreLabel coreLabel : coreLabelsList) {

            String partsOfSpeech = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if (domainSpecificTopics.contains(coreLabel.originalText())) {
                wordsWithPOSTag.add(new Nlp(coreLabel.originalText(), "NN"));
            } else {
                wordsWithPOSTag.add(new Nlp(coreLabel.originalText(), partsOfSpeech));
            }
        }
        return wordsWithPOSTag;
    }

    @Override
    //  finding the DomainSpecificTopicnames in the given question
    public List<String> getDomainSpecificTopicName() {
        String sentenceWithoutStopWords = getSentenceWithoutStopWords();
        List<String> conceptName= new ArrayList<>();
        for (int i = 0; i < domainSpecificTopics.size(); i++) {
            String pattenString = domainSpecificTopics.get(i).toLowerCase();
            Pattern pattern = Pattern.compile(pattenString);
            Matcher matcher = pattern.matcher(sentenceWithoutStopWords.toLowerCase());
            while (matcher.find()) {
                conceptName.add(domainSpecificTopics.get(i).toLowerCase());
                break;
            }
        }
        System.out.println(conceptName);
        produceMsg(conceptName);
        return conceptName;
    }


    // RabbitMq message producer method
    public void produceMsg(List<String> msg){
        log.info("Sending message");
        System.out.println("Send msg = " + msg);
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }
}