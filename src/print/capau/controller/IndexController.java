package print.capau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index1() {
		return "index";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("erro")
	public String erro() {
		return "erro/erro";
	}

	@RequestMapping("404")
	public String erro404() {
		return "erro/404";
	}

	@RequestMapping("403")
	public String erro403() {
		return "erro/403";
	}

}
