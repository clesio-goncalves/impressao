package print.capau.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	ConfiguracaoDao dao;

	@Autowired
	ImpressaoDao dao_impressao;

	@Secured("hasRole('ROLE_ADMIN')")
	@RequestMapping("diretorioLogs")
	public String diretorioLogs(Model model) {

		// Se não tiver um diretorio cadastrado
		if (dao.qntRegistro().intValue() == 0) {
			return "configuracao/logs/novo";
		} else {
			model.addAttribute("configuracao", dao.buscaConfiguracao());
			return "configuracao/logs/edita";
		}
	}

	@Secured("hasRole('ROLE_ADMIN')")
	@RequestMapping("adicionaDiretorioLogs")
	public String adiciona(@Valid Configuracao configuracao, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:diretorioLogs";
		}

		// Adiciona no banco de dados
		dao.adiciona(configuracao);
		return "redirect:diretorioLogs";
	}

	@Secured("hasRole('ROLE_ADMIN')")
	@RequestMapping("alteraDiretorioLogs")
	public String altera(@Valid Configuracao configuracao, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:diretorioLogs";
		}

		// Altera no banco de dados
		dao.altera(configuracao);
		return "redirect:diretorioLogs";
	}

	@Secured("hasRole('ROLE_ADMIN')")
	@RequestMapping("desativacaoIpv6")
	public String desativacaoIpv6(Model model) {

		model.addAttribute("impressoes", dao_impressao.desativacaoIpv6());

		return "configuracao/ipv6";
	}

}
