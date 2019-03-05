package com.stackroute.recommendationcommandservice.domain;

import com.stackroute.recommendationcommandservice.service.Actions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    UserDTO user;
    List<CommentDTO> comment;
    List<AnswerDTO> answer;
    private Actions action;
    private int questionId;
    private String question;
    private String description;
    private List<String> topics;
    private int upvotes;
    private long timestamp;
    private int downvotes;
}
