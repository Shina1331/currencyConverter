package bankingApp.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import bankingApp.model.CurrencyResponse;

@Service
public class CurrencyConverterService {

	ObjectMapper objectMapper;

	@Autowired
	JsonReader reader;

	@Autowired
	CacheManager cacheManager;

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);


	@Autowired
	MathService mathServ;

//	private final RestTemplate restTemplate;

	@Autowired
	public CurrencyConverterService(RestTemplate restTemplate, ObjectMapper om) {
//		this.restTemplate = restTemplate;
		this.objectMapper = om;
	}

//	public CurrencyResponse getExchangeRates() {
//		String url = apiUrl + "latest?access_key=" + apiKey;
//		System.out.println(url);
//		ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(url, CurrencyResponse.class);
//		return response.getBody();
//	}

	public Set<String> getAllCurrencies() {
		return reader.getCurrencyResponseFromJson().getRates().keySet();
	}

	public BigDecimal convertAmountFromTo(double amount, String from, String to) {
		double exchangeRate = reader.getCurrencyResponseFromJson().getRates().get(from);
		double res = amount * exchangeRate;
		res = res * reader.getCurrencyResponseFromJson().getRates().get(to);
		return mathServ.reduceNumbersAfterComma(res);
	}

	public void checkCache() {
		Cache cache = cacheManager.getCache("currenciesCache");
		if (cache != null) {
			CurrencyResponse cachedResponse = cache.get("currencyResponseKey", CurrencyResponse.class);
			if (cachedResponse != null) {
				System.out.println("Cache Hit: " + cachedResponse);
			} else {
				System.out.println("Cache Miss");
			}
		}

	}
}
