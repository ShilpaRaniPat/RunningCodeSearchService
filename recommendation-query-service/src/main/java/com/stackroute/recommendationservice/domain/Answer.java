package com.stackroute.recommendationservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer implements Serializable {
    User user;
    List<Comment> comments;
    private String answer;

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    private boolean accepted;
    private int upvotes;
    private int views;
    private long timestamp;
}
