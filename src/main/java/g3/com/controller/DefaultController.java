package g3.com.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@ComponentScans(value = { @ComponentScan })
public class DefaultController {
	@RequestMapping(value = { "/login"})
	public String loginPage() {
		return "login";
	}
}