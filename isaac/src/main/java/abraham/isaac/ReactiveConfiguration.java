package abraham.isaac;

import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
@EnableReactiveMongoRepositories
public class ReactiveConfiguration implements WebFluxConfigurer{

}
