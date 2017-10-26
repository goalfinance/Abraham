/**
 *
 */
package abraham.web;

import abraham.core.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author panqingrong
 */
@SpringBootApplication
@EnableDiscoveryClient
@Import({WebConfig.class, ApplicationConfig.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplateIsaac(){
        return new RestTemplate();
    }

}
