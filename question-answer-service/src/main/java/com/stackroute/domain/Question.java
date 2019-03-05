package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "latest")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question{

    //Variables
    @Id
    private int questionId;
    private String question;
    private String description;
    private List<String> topics;
    private int upvotes;
    private String timestamp;
    private int downvotes;
    User user;
    List<Comment> comment;
    List<Answer> answer;
}



