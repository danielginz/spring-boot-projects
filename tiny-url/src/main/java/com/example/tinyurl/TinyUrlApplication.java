package com.example.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinyUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyUrlApplication.class, args);
	}

}


//Run in Postman
		/*{
		"alias":"test",
		"url":"http://www.google.com/"
		}*/

//Run it from Browser
//http://tinyurl.com/KindleWireless
//http://localhost:8080/test -> you will be redirected to http://www.google.com/
//http://localhost:8080/test123
//http://localhost:8080/test456