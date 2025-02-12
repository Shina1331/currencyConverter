package bankingApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bankingApp.service.CurrencyConverterService;

@Configuration
@EnableCaching
public class CacheConfig {
	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

	@Bean
	public CacheManager cacheManager() {
		logger.info("Cache Manager created");
		return new ConcurrentMapCacheManager("currenciesCache", "financialEntitiesCache"); // In-memory cache

	}
}