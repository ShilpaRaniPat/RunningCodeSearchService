package com.stackroute.recommendationcommandservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    private int questionId;
    private String questionString;
    private long timestamp;
    private int upVote;
    private int downVote;
    @Relationship(type = "asked", direction = Relationship.INCOMING)
    private List<User> user;
    @Relationship(type = "answer_of", direction = Relationship.INCOMING)
    private List<Answer> answer;
    @Relationship(type = "viewed", direction = Relationship.INCOMING)
    private List<User> user1;
    @Relationship(type = "upvoted", direction = Relationship.INCOMING)
    private List<User> user2;
    @Relationship(type = "downvoted", direction = Relationship.INCOMING)
    private List<User> user3;

}
