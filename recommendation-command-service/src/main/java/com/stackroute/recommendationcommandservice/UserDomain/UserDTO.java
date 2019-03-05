package com.stackroute.recommendationcommandservice.UserDomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
     /*
    assigning the primary keys
     */


    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> interests;

}
