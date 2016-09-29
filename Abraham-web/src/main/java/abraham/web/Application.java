/**
 * 
 */
package abraham.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import abraham.core.ApplicationConfig;

/**
 * @author panqingrong
 *
 */
@SpringBootApplication
@Import({WebConfig.class, ApplicationConfig.class})
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}

}
