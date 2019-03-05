package com.stackroute.nlp.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nlp {
    private String originalWord;          //Keyword is stored in this String
    private String partsOfSpeech;         //Parts of speech og the extracted keyword
}