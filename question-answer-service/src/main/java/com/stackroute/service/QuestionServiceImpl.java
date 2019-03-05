package com.stackroute.service;

import com.stackroute.domain.*;
import com.stackroute.exceptions.*;
import com.stackroute.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${jsd.rabbitmq.exchange}")
    private String exchange1;

    @Value("${jsd.rabbitmq.routingkey}")
    private String routingKey1;

    @Value("${jse.rabbitmq.exchange}")
    private String exchange2;

    @Value("${jse.rabbitmq.routingkey}")
    private String routingKey2;

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    //Overriden method for posting a new question
    @Override
    public Question addQuestion(Question questionObject) throws QuestionAlreadyExistsException {
        Timestamp timestamp = generateTimestamp();
        String time = String.valueOf(timestamp);
        System.out.println("added "+time);
        if (questionRepository.existsByQuestion(questionObject.getQuestion())) {
            throw new QuestionAlreadyExistsException(questionObject.getQuestion()+" already exists");
        }
        questionObject.setQuestionId(questionRepository.findAll().size() + 1);
        Question savedQuestion = questionRepository.save(questionObject);
        QuestionDTO questionDTO = new QuestionDTO(Actions.POST_QUESTION, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
        produceMsg(questionDTO);
        return savedQuestion;
    }

    //Overriden method to add answer
    @Override
    public Question addAnswer(int questionId, Answer answer) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            if (question.getAnswer() != null) {
                List<Answer> answers = question.getAnswer();
                answers.add(answer);
                question.setAnswer(answers);
            }
            else {
                List<Answer> answers = new ArrayList<>();
                answers.add(answer);
                question.setAnswer(answers);
            }
            Question savedQuestion = questionRepository.save(question);
            QuestionDTO questionDTO = new QuestionDTO(Actions.QUESTION_ANSWER, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
            produceMsg(questionDTO);
            return savedQuestion;
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    //Overriden method to get a particular question
    @Override
    public Question getQuestion(int questionId) throws QuestionNotFoundException {
        if(questionRepository.findById(questionId)==null){
            throw new QuestionNotFoundException("Question not found");
        }
        else {
            return questionRepository.findById(questionId).get();
        }
    }

    //Overriden method to comment to a question
    @Override
    public Question addQuestionComment(int questionId, Comment comment) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            if (question.getComment() != null) {
                List<Comment> comments = question.getComment();
                comments.add(comment);
                question.setComment(comments);
            } else {
                List<Comment> comments = new ArrayList<>();
                comments.add(comment);
                question.setComment(comments);
            }
            Question savedQuestion = questionRepository.save(question);
            QuestionDTO questionDTO = new QuestionDTO(Actions.QUESTION_COMMENT, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
            produceMsg(questionDTO);
            return savedQuestion;
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    //Overriden method to add reply to question comment
    @Override
    public Question addQuestionCommentReply(int questionId, String comment, List<Replies> replies) throws QuestionNotFoundException,CommentNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Comment> comments = question.getComment();
            for (Comment comment1: comments) {
                if(comment1.getComment().equals(comment)){
                    flag = true;
                    if (comment1.getReplies()==null){
                        comment1.setReplies(replies);
                    }
                    else {
                        replies.addAll(comment1.getReplies());
                        comment1.setReplies(replies);
                    }
                }
            }
            if (flag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.QUESTION_COMMENT_REPLY, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else {
                throw new CommentNotFoundException("Comment does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }

    }

    //Overriden method to add comment to answer
    @Override
    public Question addAnswerComment(int questionId, String answer, List<Comment> comment) throws QuestionNotFoundException,AnswerNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    flag = true;
                    if (answer1.getComments()==null){
                        answer1.setComments(comment);
                    }
                    else {
                        comment.addAll(answer1.getComments());
                        answer1.setComments(comment);
                    }
                }
            }
            if (flag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.ANSWER_COMMENT, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    //Overriden method to add reply for answer comment
    @Override
    public Question addAnswerCommentReply(int questionId, String answer, List<Comment> comment)throws QuestionNotFoundException,AnswerNotFoundException,CommentNotFoundException {
        boolean answerFlag = false;
        boolean commentFlag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if (answers==null){
                throw new AnswerNotFoundException("Answer not found");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    answerFlag = true;
                    List<Comment> comments = answer1.getComments();
                    if (comments==null){
                        throw new CommentNotFoundException("Comment not found");
                    }
                    Comment comment2 = comment.get(0);
                    for (Comment comment1:comments) {
                        if(comment1.getComment().equals(comment2.getComment())){
                            commentFlag = true;
                            if(comment1.getReplies()==null){
                                comment1.setReplies(comment2.getReplies());
                            }
                            else{
                                List<Replies> repliesList = comment1.getReplies();
                                repliesList.addAll(comment2.getReplies());
                                comment1.setReplies(repliesList);
                            }
                        }
                    }
                }
            }
            if (answerFlag && commentFlag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.ANSWER_COMMENT_REPLY, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else if (!commentFlag){
                throw new CommentNotFoundException("Comment not found");
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    //Overriden method to upvote a question
    @Override
    public Question addQuestionUpvote(int questionId) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            int upvotes = question.getUpvotes();
            question.setUpvotes(upvotes+1);
            Question savedQuestion = questionRepository.save(question);
            QuestionDTO questionDTO = new QuestionDTO(Actions.QUESTION_UPVOTE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
            produceMsg(questionDTO);
            return savedQuestion;
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    //Overriden method to downvote a question
    @Override
    public Question addQuestionDownvote(int questionId) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            int downvotes = question.getDownvotes();
            question.setDownvotes(downvotes+1);
            Question savedQuestion = questionRepository.save(question);
            QuestionDTO questionDTO = new QuestionDTO(Actions.QUESTION_DOWNVOTE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
            produceMsg(questionDTO);
            return savedQuestion;
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    //Overriden method to upvote an answer
    @Override
    public Question addAnswerUpvote(int questionId, String answer) throws QuestionNotFoundException,AnswerNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if(answers==null){
                throw new AnswerNotFoundException("Answer not exists");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    flag = true;
                    int upvotes = answer1.getUpvotes();
                    answer1.setUpvotes(upvotes+1);
                }
            }
            if (flag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.ANSWER_UPVOTE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public Question addAnswerDownvote(int questionId, String answer) throws QuestionNotFoundException, AnswerNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if(answers==null){
                throw new AnswerNotFoundException("Answer not exists");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    flag = true;
                    int downvotes = answer1.getDownvotes();
                    answer1.setDownvotes(downvotes+1);
                }
            }
            if (flag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.ANSWER_DOWNVOTE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    //Overriden method to add likes for question comment
    @Override
    public Question addQuestionCommentLikes(int questionId, String comment) throws QuestionNotFoundException,CommentNotFoundException{
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Comment> comments = question.getComment();
            if (comments==null){
                throw new CommentNotFoundException("Comment does not exists");
            }
            for (Comment comment1: comments) {
                if(comment1.getComment().equals(comment)){
                    flag = true;
                    long likes = comment1.getLikes();
                    comment1.setLikes(likes+1);
                }
            }
            if (flag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.QUESTION_COMMENT_LIKE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else {
                throw new CommentNotFoundException("Comment does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    //Overriden method to add likes for question comment reply
    @Override
    public Question addQuestionCommentReplyLikes(int questionId, Comment comment) throws QuestionNotFoundException,CommentNotFoundException,ReplyNotFoundException{
        boolean commentFlag = false;
        boolean replyFlag  = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Comment> comments = question.getComment();
            if (comments==null){
                throw new CommentNotFoundException("Comment not found");
            }
            for (Comment comment1: comments) {
                if(comment1.getComment().equals(comment.getComment())){
                    commentFlag = true;
                    if (comment1.getReplies()==null){
                        throw new ReplyNotFoundException("Reply not found");
                    }
                    else {
                        List<Replies> repliesList = comment1.getReplies();
                        Replies replies = comment.getReplies().get(0);
                        for (Replies reply:repliesList) {
                            if(reply.getReply().equals(replies.getReply())) {
                                replyFlag = true;
                                long likes = reply.getLikes();
                                reply.setLikes(likes+1);
                            }
                        }
                    }
                }
            }
            if (commentFlag && replyFlag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.QUESTION_COMMENT_REPLY_LIKE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else if (!replyFlag){
                throw new ReplyNotFoundException("Reply not found");
            }
            else {
                throw new CommentNotFoundException("Comment does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    //Overriden method to add likes for answer comment
    @Override
    public Question addAnswerCommentLikes(int questionId, Answer answer) throws QuestionNotFoundException,AnswerNotFoundException,CommentNotFoundException{
        boolean answerFlag = false;
        boolean commentFlag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if (answers==null){
                throw new AnswerNotFoundException("Answer not found");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer.getAnswer())){
                    answerFlag = true;
                    List<Comment> comments = answer1.getComments();
                    if (comments==null){
                        throw new CommentNotFoundException("Comment not found");
                    }
                    Comment comment2 = comments.get(0);
                    for (Comment comment1:comments) {
                        if(comment1.getComment().equals(comment2.getComment())){
                            commentFlag = true;
                            long likes = comment1.getLikes();
                            comment1.setLikes(likes+1);
                        }
                    }
                }
            }
            if (answerFlag && commentFlag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.ANSWER_COMMENT_LIKE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else if (!commentFlag){
                throw new CommentNotFoundException("Comment not found");
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    //Overriden method to add likes for answer comment reply
    @Override
    public Question addAnswerCommentReplyLikes(int questionId, Answer answer) throws QuestionNotFoundException,AnswerNotFoundException,CommentNotFoundException,ReplyNotFoundException {
        boolean answerFlag = false;
        boolean commentFlag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if (answers==null){
                throw new AnswerNotFoundException("Answer not found");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer.getAnswer())){
                    answerFlag = true;
                    List<Comment> comments = answer1.getComments();
                    if (comments==null){
                        throw new CommentNotFoundException("Comment not found");
                    }
                    Comment comment2 = comments.get(0);
                    for (Comment comment1:comments) {
                        if(comment1.getComment().equals(comment2.getComment())){
                            commentFlag = true;
                            List<Replies> repliesList = comment1.getReplies();
                            if (repliesList==null){
                                throw new ReplyNotFoundException("Reply not found");
                            }
                            else {
                                List<Replies> replies = comment2.getReplies();
                                Replies reply = replies.get(0);
                                for (Replies replies1 : repliesList){
                                    if (replies1.getReply().equals(reply.getReply())){
                                        long likes = replies1.getLikes();
                                        replies1.setLikes(likes+1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (answerFlag && commentFlag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.ANSWER_COMMENT_REPLY_LIKE, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else if (!commentFlag){
                throw new CommentNotFoundException("Comment not found");
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    //Overriden method for adding accepted answer
    @Override
    public Question addQuestionAnswerAccepted(int questionId, String answer) throws QuestionNotFoundException,AnswerNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if(answers==null){
                throw new AnswerNotFoundException("Answer not exists");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    flag = true;
                    if(!answer1.isAccepted()){
                        answer1.setAccepted(true);
                    }
                    else{
                        answer1.setAccepted(false);
                    }
                }
            }
            if (flag){
                Question savedQuestion = questionRepository.save(question);
                QuestionDTO questionDTO = new QuestionDTO(Actions.ANSWER_ACCEPT, savedQuestion.getQuestionId(), savedQuestion.getQuestion(), savedQuestion.getDescription(), savedQuestion.getTopics(), savedQuestion.getUpvotes(), savedQuestion.getTimestamp(), savedQuestion.getDownvotes(), savedQuestion.getUser(), savedQuestion.getComment(), savedQuestion.getAnswer());
                produceMsg(questionDTO);
                return savedQuestion;
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Timestamp generateTimestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //via Date
        Date date = new Date();
        new Timestamp(date.getTime());

        //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        timestamp.getTime();
        return timestamp;

    }

    //RabbitMq message producer method
    public void produceMsg(QuestionDTO msg){
        log.info("Sending message");
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        amqpTemplate.convertAndSend(exchange1, routingKey1, msg);
        amqpTemplate.convertAndSend(exchange2, routingKey2, msg);
        System.out.println("Send msg = " + msg);
    }
}
