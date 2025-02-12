package bankingApp.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bankingApp.model.FinancialEntity;
import bankingApp.service.CurrencyConverterService;
import bankingApp.service.FinancialEntityService;

@Controller
public class Home {

	@Autowired
	CurrencyConverterService converterServ;

	@Autowired
	FinancialEntityService entityServ;

	@GetMapping("/home")
	public String showHomeScreen(Model model) {
		model.addAttribute("currs", converterServ.getAllCurrencies());
		return "home";
	}

	@PostMapping("/convert")
	public String convert(@RequestParam("currFrom") String currFrom, @RequestParam("currTo") String currTo,
			@RequestParam("amount") double amount, Model model, RedirectAttributes redirectAttr) {
		redirectAttr.addFlashAttribute("result", converterServ.convertAmountFromTo(amount, currFrom, currTo));
		redirectAttr.addFlashAttribute("currFrom", currFrom);
		redirectAttr.addFlashAttribute("currTo", currTo);
		redirectAttr.addFlashAttribute("amount", amount);
		FinancialEntity entity = entityServ.getEntityByAlphabeticCode(currTo);
		redirectAttr.addFlashAttribute("entity", entity);
		redirectAttr.addFlashAttribute("flagExists", entityServ.flagExists(entity.getAlphabeticCode()));
		if (entity.getEntities().size() > 1) {
			redirectAttr.addFlashAttribute("secondFlag", entityServ.flagExists(entity.getAlphabeticCode() + 2));
		}

		return "redirect:/home";
	}

}
