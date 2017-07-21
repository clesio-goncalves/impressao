package print.capau.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import print.capau.dao.ConfiguracaoDao;
import print.capau.dao.ImpressaoDao;
import print.capau.modelo.Configuracao;

@Transactional
@Controller
public class ConfiguracaoController {

	@Autowired
	private ConfiguracaoDao dao;

	@Autowired
	private ImpressaoDao dao_impressao;

	@RequestMapping("diretorioLogs")
	private String diretorioLogs(Model model) {

		// Se não tiver um diretorio cadastrado
		if (dao.qntRegistro().intValue() == 0) {
			return "configuracao/logs/novo";
		} else {
			model.addAttribute("configuracao", dao.buscaDiretorio());
			return "configuracao/logs/edita";
		}
	}

	@RequestMapping("adicionaDiretorioLogs")
	private String adiciona(@Valid Configuracao configuracao, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:diretorioLogs";
		}

		// Adiciona no banco de dados
		dao.adiciona(configuracao);
		return "redirect:diretorioLogs";
	}

	@RequestMapping("alteraDiretorioLogs")
	private String altera(@Valid Configuracao configuracao, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:diretorioLogs";
		}

		// Altera no banco de dados
		dao.altera(configuracao);
		return "redirect:diretorioLogs";
	}

	@RequestMapping("desativacaoIpv6")
	private String desativacaoIpv6(Model model) {

		model.addAttribute("impressoes", dao_impressao.desativacaoIpv6());

		return "configuracao/ipv6";
	}

}
