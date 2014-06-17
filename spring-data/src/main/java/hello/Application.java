package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableMongoRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		// String webPort = System.getenv("PORT");
		// if (webPort == null || webPort.isEmpty()) {
		// webPort = "80";
		// }
		// System.setProperty("server.port", webPort);

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Filter simpleCORSFilter() {
		SimpleCORSFilter simpleCORSFilter = new SimpleCORSFilter();
		return simpleCORSFilter;
	}
}
