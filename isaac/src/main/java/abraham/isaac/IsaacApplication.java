package abraham.isaac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IsaacApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaacApplication.class, args);
	}
}
