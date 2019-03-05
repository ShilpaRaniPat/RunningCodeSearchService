package com.stackroute.searchservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
   //private member variable declaration//
   private String  firstname;
   private String emailaddress;
   private String imageurl;
}
