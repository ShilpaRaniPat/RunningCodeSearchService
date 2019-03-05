package com.stackroute.searchservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    private String answer;
    private boolean accepted;
    private int upvotes;
    private int views;
    private long timestamp;

    private List<Comment> comments;
    private User user;

}
