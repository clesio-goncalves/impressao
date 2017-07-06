package print.capau.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import print.capau.dao.SetorDao;
import print.capau.modelo.Setor;

@Transactional
@Controller
public class SetorController {

	@Autowired
	private SetorDao dao;

	@RequestMapping("novoSetor")
	public String setor() {
		return "setor/novo";
	}

	@RequestMapping("adicionaSetor")
	public String adiciona(@Valid Setor setor, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:novoSetor";
		}

		// Adiciona no banco de dados
		dao.adiciona(setor);
		return "redirect:listaSetores";
	}

	@RequestMapping("listaSetores")
	public String lista(Model model) {
		model.addAttribute("setores", dao.lista());
		return "setor/lista";
	}

}
