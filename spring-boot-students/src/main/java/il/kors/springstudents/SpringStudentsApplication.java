package il.kors.springstudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
@SpringBootApplication


public class SpringStudentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudentsApplication.class, args);
	}

}

//install JsonViewer to the Chrome

//http://localhost:8080/api/v1/students..