package com.stackroute.recommendationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Reply implements Serializable {
    User user;
    private String reply;
    private long likes;
    private long timestamp;
}