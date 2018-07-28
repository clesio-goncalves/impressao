package print.capau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import print.capau.dao.ImpressoraDao;
import print.capau.dao.SetorDao;
import print.capau.modelo.Impressora;
import print.capau.modelo.Usuario;
import print.capau.relatorio.GeradorRelatorio;

@Transactional
@Controller
public class ImpressoraController {

	private List<Impressora> lista_impressoras;

	@Autowired
	ImpressoraDao dao;

	@Autowired
	SetorDao dao_setor;

	@RequestMapping("listaImpressoras")
	public String lista(Model model) {

		lista_impressoras = dao.lista();

		for (Impressora impressora : lista_impressoras) {
			impressora.setTotal_impressao(dao.qntImpressao(impressora));
		}

		model.addAttribute("impressoras", lista_impressoras);
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

	@RequestMapping("relatorioImpressora")
	public void relatorio(HttpServletRequest request, HttpServletResponse response) {

		String nomeRelatorio = "Relatório de Impressoras";
		String nomeArquivo = request.getServletContext().getRealPath("/resources/relatorio/impressoras.jasper");
		Map<String, Object> parametros = new HashMap<String, Object>();
		JRBeanCollectionDataSource relatorio = new JRBeanCollectionDataSource(lista_impressoras);

		// Pego o usuário logado
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Parâmetros do relatório
		parametros.put("imagem_logo",
				request.getServletContext().getRealPath("/resources/imagens/relatorio_impressoras.png"));
		parametros.put("nome_usuario", usuario.getNome());
		parametros.put("login_usuario", usuario.getUsuario());

		GeradorRelatorio gerador = new GeradorRelatorio(nomeRelatorio, nomeArquivo, parametros, relatorio);
		gerador.geraPDFParaOutputStream(response);

	}

}
