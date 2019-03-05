package com.stackroute.searchservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Replies {
    //private member variable declaration//
    private String reply;
    private int likes;
    private long timestamp;
    private User user;
}
