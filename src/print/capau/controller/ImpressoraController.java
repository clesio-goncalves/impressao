package print.capau.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import print.capau.dao.ImpressoraDao;
import print.capau.dao.SetorDao;
import print.capau.modelo.Impressora;

@Transactional
@Controller
public class ImpressoraController {

	@Autowired
	private ImpressoraDao dao;

	@Autowired
	private SetorDao dao_setor;

	List<Impressora> lista;

	@RequestMapping("listaImpressoras")
	public String lista(Model model) {

		lista = new ArrayList<Impressora>();
		lista = dao.lista();

		for (Impressora impressora : lista) {
			impressora.setTotal_impressao(dao.qntImpressao(impressora));
		}

		model.addAttribute("impressoras", lista);
		return "impressora/lista";
	}

	@RequestMapping("editaImpressora")
	public String edita(Long id, Model model) {
		// Verifica se há setores cadastrados
		if (dao_setor.lista().size() == 0) {
			return "redirect:novoSetor";
		} else {
			model.addAttribute("impressora", dao.buscaPorId(id));
			model.addAttribute("setores", dao_setor.lista());
			return "impressora/vincula";
		}
	}

	@RequestMapping("vinculaImpressora")
	public String vincular(@Valid Impressora impressora, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:editaImpressora?id=" + impressora.getId();
		}

		dao.altera(impressora);
		return "redirect:listaImpressoras";
	}

}
