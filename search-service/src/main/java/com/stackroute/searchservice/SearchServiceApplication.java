package com.stackroute.searchservice;

import com.stackroute.searchservice.domain.Question;
import com.stackroute.searchservice.domain.Topic;
import com.stackroute.searchservice.domain.User;
import com.stackroute.searchservice.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SearchServiceApplication {
	@Autowired
	SearchRepository searchRepository;


	public static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}
}











