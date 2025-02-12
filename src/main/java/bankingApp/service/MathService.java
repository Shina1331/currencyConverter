package bankingApp.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;
@Service
public class MathService {

	public BigDecimal reduceNumbersAfterComma(double number) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}
	
}
