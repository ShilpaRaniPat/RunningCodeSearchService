package com.stackroute.recommendationcommandservice.controller;


import com.stackroute.recommendationcommandservice.service.RecommendationCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RecommendationController {
    private RecommendationCommandService homeService;
    ResponseEntity responseEntity;

    RecommendationController(RecommendationCommandService homeService) {
        this.homeService = homeService;
    }

//    //method to add USER//
//    @PostMapping("/userDTO")
//    public ResponseEntity<String> addUser(@RequestBody User user) {
//
//        try {
//            homeService.saveUserToDb(user);
//            responseEntity = new ResponseEntity<String>("UserDTO saved sucessfully", HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<String>("Error occured while saving", HttpStatus.BAD_GATEWAY);
//        }
//        return responseEntity;
//    }
//
//
//    //method to add QUESTION//
//    @PostMapping("/question")
//    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
//
//        try {
//            homeService.saveQuestionToDb(question);
//            responseEntity = new ResponseEntity<String>("Question saved sucessfully", HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<String>("Error occured while saving", HttpStatus.BAD_GATEWAY);
//        }
//        return responseEntity;
//    }
//
//    //method to add ANSWER//
//    @PostMapping("/answerDTO")
//    public ResponseEntity<String> addAnswer(@RequestBody Answer answer) {
//        try {
//            homeService.saveAnswerToDb(answer);
//            responseEntity = new ResponseEntity<String>("AnswerDTO saved sucessfully", HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<String>("Error occured while saving", HttpStatus.BAD_GATEWAY);
//        }
//        return responseEntity;
//    }
//
//
//    //method to get USERS//
//    @GetMapping("/users")
//    public ResponseEntity<Collection<User>> getAllUsers() {
//        ResponseEntity<Collection<User>> responseEntity;
//        try {
//            log.info("Fetching userDTO nodes");
//            responseEntity = new ResponseEntity(homeService.getUsers(), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity(Collections.emptyList(), HttpStatus.NOT_FOUND);
//        }
//        return responseEntity;
//    }
//
//    //method to get QUESTIONS//
//    @GetMapping("/questions")
//    public ResponseEntity<Collection<Question>> getAllQuestions() {
//        ResponseEntity<Collection<Question>> responseEntity;
//        try {
//            log.info("Fetching question nodes");
//            responseEntity = new ResponseEntity(homeService.getQuestions(), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity(Collections.emptyList(), HttpStatus.NOT_FOUND);
//        }
//        return responseEntity;
//    }
//
//
//    //method to get ANSWERS//
//    @GetMapping("/answers")
//    public ResponseEntity<Collection<Answer>> getAllAnswers() {
//        ResponseEntity<Collection<Answer>> responseEntity;
//        try {
//            log.info("Fetching answerDTO nodes");
//            responseEntity = new ResponseEntity(homeService.getAnswers(), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity(Collections.emptyList(), HttpStatus.NOT_FOUND);
//        }
//        return responseEntity;
//    }
//
//
//    //method to get USER using REPUTATION//
//    @GetMapping("/userDTO/{reputation}")
//    public ResponseEntity<User> getUserByReputation(@PathVariable int reputation) {
//
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.getByUser(reputation), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
//        }
//        return responseEntity;
//    }
//
//    //method to create relationship FOLLOWS between userDTO and topic//


//    @GetMapping("/follows/{userName}/{name}")
//    public ResponseEntity<User> CreateRelationshipfollows(@PathVariable String userName, @PathVariable String name) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userFollowsTopic(userName, name), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }

    //
//    //method to create relationship QUESTION_OF between question and topic//
//    @GetMapping("/belongs/{questionId}/{name}")
//    public ResponseEntity<Question> CreateRelationshipbelongs(@PathVariable int questionId, @PathVariable String name) {
//        try {
//
//            responseEntity = new ResponseEntity<Question>(homeService.questionBelongsTopic(questionId, name), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<Question>(new Question(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//
//    //method to create relationship ANSWERED between userDTO and answerDTO//
//    @GetMapping("/answered/{userName}/{answerId}")
//    public ResponseEntity<User> CreateRelationshipanswered(@PathVariable String userName, @PathVariable int answerId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userAnsweredAnswer(userName, answerId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//    //method to create relationship VIEWED between userDTO and question//
//    @GetMapping("/viewed/{userName}/{questionId}")
//    public ResponseEntity<User> CreateRelationshipviewed(@PathVariable String userName, @PathVariable int questionId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userViewedQuestion(userName, questionId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//
//    //method to create relationship ANSWER_OF between answerDTO and question//
//    @GetMapping("/answerof/{answerId}/{questionId}")
//    public ResponseEntity<Answer> CreateRelationshipanswerof(@PathVariable int answerId, @PathVariable int questionId) {
//        try {
//
//            responseEntity = new ResponseEntity<Answer>(homeService.answerIsAnswerOfQuestion(answerId, questionId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<Answer>(new Answer(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//
//    //method to create relationship ASKED between userDTO and question//
//    @GetMapping("/asked/{userName}/{questionId}")
//    public ResponseEntity<User> CreateRelationshipasked(@PathVariable String userName, @PathVariable int questionId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userAskedQuestion(userName, questionId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//    //method to create relationship ACCEPTED between userDTO and answerDTO//
//    @GetMapping("/accepted/{userName}/{answerId}")
//    public ResponseEntity<User> CreateRelationshipaccepted(@PathVariable String userName, @PathVariable int answerId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userAcceptedAnswer(userName, answerId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//
//    //method to create relationship UPVOTED between userDTO and answerDTO//
//    @GetMapping("/upvoted/{userName}/{answerId}")
//    public ResponseEntity<User> CreateRelationshipupvoted(@PathVariable String userName, @PathVariable int answerId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userUpvotedAnswer(userName, answerId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//    //method to create relationship DOWNVOTED between userDTO and answerDTO//
//    @GetMapping("/downvoted/{userName}/{answerId}")
//    public ResponseEntity<User> CreateRelationshipdownvoted(@PathVariable String userName, @PathVariable int answerId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userDownvotedAnswer(userName, answerId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//    //method to create relationship UPVOTE between userDTO and question//
//    @GetMapping("/Upvoted/{userName}/{questionId}")
//    public ResponseEntity<User> CreateRelationshipUpvoted(@PathVariable String userName, @PathVariable int questionId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userUpvoteQuestion(userName, questionId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
//    //method to create relationship DOWNVOTE between userDTO and question//
//    @GetMapping("/Downvoted/{userName}/{questionId}")
//    public ResponseEntity<User> CreateRelationshipDownvoted(@PathVariable String userName, @PathVariable int questionId) {
//        try {
//
//            responseEntity = new ResponseEntity<User>(homeService.userDownvoteQuestion(userName, questionId), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<User>(new User(), HttpStatus.NOT_MODIFIED);
//        }
//        return responseEntity;
//    }
//
}