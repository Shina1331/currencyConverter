package bankingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import bankingApp.service.CurrencyConverterService;

@SpringBootApplication
@EnableCaching
public class App {
	
	@Autowired
	 CurrencyConverterService service;
	 
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
      
    }
    

    
    
    
}