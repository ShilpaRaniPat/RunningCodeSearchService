package com.stackroute.searchservice.domain;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    //private member variable declaration//
    private String question;
    private String description;
    private int upvotes;
    private long timestamp;
    private int downvote;
    private List<Answer> answers;
    private List<Comments> comments;
    private User user;
}
