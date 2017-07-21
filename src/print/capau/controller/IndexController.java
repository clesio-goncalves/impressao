package print.capau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("index")
	private String index() {
		return "index";
	}
	
	@RequestMapping("erro")
	private String erro() {
		return "erro/erro";
	}
	
	@RequestMapping("404")
	private String erro404() {
		return "erro/404";
	}

}
