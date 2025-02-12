package bankingApp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bankingApp.model.CurrencyResponse;
import bankingApp.model.FinancialEntity;
@Service
public class JsonReader {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);
	ObjectMapper objectMapper;
	
	public JsonReader(ObjectMapper om) {
		this.objectMapper = om;
	}
	
	@Cacheable(value = "currenciesCache", key = "'currencyResponseKey'")
	public CurrencyResponse getCurrencyResponseFromJson() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("currencies.json")) {
			if (inputStream == null) {
				logger.error("Resource 'currencies.json' not found.");
				return null;
			}
			return objectMapper.readValue(inputStream, CurrencyResponse.class);
		} catch (IOException e) {
			logger.error("Error reading 'currencies.json' file: " + e.getMessage());
		}
		return null;
	}
	
	@Cacheable(value="financialEntitiesCache", key = "'financialEntitiesCacheKey'")
	public List<FinancialEntity> getFinancialEntitiesFromJson() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("currency-codes.json")) {
			if (inputStream == null) {
				logger.error("Resource 'currency-codes.json' not found.");
				return null;
			}
			return objectMapper.readValue(inputStream,
					objectMapper.getTypeFactory().constructCollectionType(List.class, FinancialEntity.class));
		} catch (IOException e) {
			logger.error("Error reading 'currency-codes.json' file: " + e.getMessage());
		}
		return null;
	}
	
	
}
