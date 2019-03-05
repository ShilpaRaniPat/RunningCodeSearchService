package com.stackroute.recommendationcommandservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    //Variables
    private String email;
    private String firstName;
    private String imageUrl;
}
