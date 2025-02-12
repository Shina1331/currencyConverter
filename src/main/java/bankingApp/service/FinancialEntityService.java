package bankingApp.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bankingApp.model.CurrencyResponse;
import bankingApp.model.FinancialEntity;

@Service
public class FinancialEntityService {

	@Autowired
	JsonReader reader;

	public FinancialEntity getEntityByAlphabeticCode(String code) {
		List<FinancialEntity> allEntities = reader.getFinancialEntitiesFromJson();
		Collections.sort(allEntities, Comparator.comparing(FinancialEntity::getAlphabeticCode));
		int low = 0;
		int high = allEntities.size() - 1;

		while (low <= high) {
			int middle = (low + high) / 2;
			FinancialEntity middleEntity = allEntities.get(middle);

			if (middleEntity.getAlphabeticCode().equals(code)) {
				return middleEntity;
			}
			if (middleEntity.getAlphabeticCode().compareTo(code) < 0) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		throw new NoSuchElementException("FinancialEntity with alphabetic code " + code + " not found.");
	}

	public Boolean flagExists(String alphabeticalCode) {
		String pathToFlag = "src/main/resources/static/flags/" + alphabeticalCode + ".png";
		File file = new File(pathToFlag);
		return file.exists();
	}

}
