package com.stackroute.userauthenticationservice.model;



        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.springframework.data.annotation.Id;
        import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO  {


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
