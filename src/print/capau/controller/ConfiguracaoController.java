package print.capau.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import print.capau.dao.ImpressaoDao;

@Transactional
@Controller
public class ConfiguracaoController {

	@Autowired
	private ImpressaoDao dao;

	@RequestMapping("desativacaoIpv6")
	public String desativacaoIpv6(Model model) {

		model.addAttribute("impressoes", dao.desativacaoIpv6());

		return "configuracao/ipv6";
	}

}
