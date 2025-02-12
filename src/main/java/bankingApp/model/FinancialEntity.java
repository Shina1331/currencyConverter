package bankingApp.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialEntity {

	@JsonProperty("entity")
	private List<String> entities;
	@JsonProperty("currency_name")
	private String currencyName;
	@JsonProperty("alphabetic_code")
	private String alphabeticCode;
		
	
	public List<String> getEntities() {
		return entities;
	}
	public void addEntity(String entity) {
		entities.add(entity);
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getAlphabeticCode() {
		return alphabeticCode;
	}
	public void setAlphabeticCode(String alphabeticCode) {
		this.alphabeticCode = alphabeticCode;
	}
	
	
	
	
}
