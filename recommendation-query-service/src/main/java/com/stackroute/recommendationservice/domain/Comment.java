package com.stackroute.recommendationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document("comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    User user;
    List<Reply> replies;
    private String reply;
    private long timestamp;
    private long likes;
}