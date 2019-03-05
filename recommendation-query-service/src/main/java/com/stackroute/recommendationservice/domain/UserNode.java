package com.stackroute.recommendationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;

@NodeEntity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNode implements Serializable {
    @Id
    String username;
    int reputation;
}
