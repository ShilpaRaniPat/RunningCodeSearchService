package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Answer{
    private String answer;
    private boolean accepted;
    private int upvotes;
    private int downvotes;
    private int views;
    private String timestamp;
    User user;
    List<Comment> comments;
}
