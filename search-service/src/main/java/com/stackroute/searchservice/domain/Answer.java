package com.stackroute.searchservice.domain;

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
public class Answer {
    //private member variable declaration//
    private String answer;
    private boolean accepted;
    List<Comments> comments;
    private int upvotes;
    private int views;
    private long timestamp;
    private User user;

}
