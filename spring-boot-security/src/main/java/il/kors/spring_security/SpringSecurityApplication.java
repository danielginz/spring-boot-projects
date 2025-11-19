package il.kors.spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}


//new users are added in Postman via "http://localhost:8080/api/v1/apps/new-user"
//the password for all is password1
//use user with encrypted password only