package com.stackroute.searchservice.model;

import com.stackroute.searchservice.service.Actions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

    @Document
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class QuestionDTO {
        private Actions action;
        private int questionId;
        private String question;
        private String description;
        private List<String> topics;
        private int upvotes;
        private long timestamp;
        private int downvotes;
        private User user;
        private List<Comment> comment;
        private List<Answer> answer;
    }


