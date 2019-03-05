package com.stackroute.recommendationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionNode {
    @Id
    long questionId;
    String question;
    long timestamp;
    int upvote;
    int downvote;
}
