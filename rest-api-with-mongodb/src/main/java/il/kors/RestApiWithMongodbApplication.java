package il.kors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RestApiWithMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWithMongodbApplication.class, args);
	}

}

