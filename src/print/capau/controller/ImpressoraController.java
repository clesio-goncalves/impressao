package print.capau.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import print.capau.dao.ConnectionFactory;
import print.capau.dao.ImpressoraDao;
import print.capau.dao.SetorDao;
import print.capau.modelo.Impressora;
import print.capau.modelo.Usuario;
import print.capau.relatorio.GeradorRelatorio;

@Transactional
@Controller
public class ImpressoraController {

	@Autowired
	ImpressoraDao dao;

	@Autowired
	SetorDao dao_setor;

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

	@RequestMapping("relatorioImpressora")
	public void relatorio(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		try {

			String nomeArquivo = request.getServletContext().getRealPath("/resources/relatorio/impressoras.jasper");
			Map<String, Object> parametros = new HashMap<String, Object>();
			Connection connection = new ConnectionFactory().getConnection();

			// Pego o usuário da sessão
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

			parametros.put("imagem_logo",
					request.getServletContext().getRealPath("/resources/imagens/relatorio_impressoras.png"));
			parametros.put("nome_usuario", usuario.getNome());
			parametros.put("login_usuario", usuario.getUsuario());

			GeradorRelatorio gerador = new GeradorRelatorio(nomeArquivo, parametros, connection);
			gerador.geraPDFParaOutputStream(response.getOutputStream());

			connection.close();

		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}

	}

}
