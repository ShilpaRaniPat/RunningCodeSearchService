package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    /*
    assigning the primary keys
     */

    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> interests;

}
