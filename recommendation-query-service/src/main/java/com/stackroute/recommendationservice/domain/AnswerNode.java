package com.stackroute.recommendationservice.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class AnswerNode {
    @Id
    long answerId;
    String answer;
    boolean accepted;
}
