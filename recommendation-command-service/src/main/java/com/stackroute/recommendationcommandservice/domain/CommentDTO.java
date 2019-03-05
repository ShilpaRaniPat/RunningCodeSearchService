package com.stackroute.recommendationcommandservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    UserDTO user;
    List<RepliesDTO> replies;
    private String comment;
    private long timestamp;
    private long likes;
}
