package bankingApp;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class AppConfig {

//	private final String apiUrl = "http://data.fixer.io/api/";

	private static AppConfig config;

	public AppConfig() {
	}

	public static AppConfig getInstance() {
		if (config == null) {
			return new AppConfig();
		} else
			return config;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
//	public String getApiUrl() {
//		return apiUrl;
//	}

}