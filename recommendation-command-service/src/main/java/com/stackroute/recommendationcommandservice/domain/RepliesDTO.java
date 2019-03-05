package com.stackroute.recommendationcommandservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepliesDTO {
    UserDTO user;
    private String reply;
    private long likes;
    private long timestamp;
}
