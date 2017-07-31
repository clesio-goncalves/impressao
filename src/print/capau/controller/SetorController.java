package print.capau.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
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
import print.capau.dao.SetorDao;
import print.capau.modelo.Setor;
import print.capau.modelo.Usuario;
import print.capau.relatorio.GeradorRelatorio;

@Transactional
@Controller
public class SetorController {

	@Autowired
	SetorDao dao;

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

	@RequestMapping("removeSetor")
	public String remove(Setor setor) {
		dao.remove(setor);
		return "redirect:listaSetores";
	}

	@RequestMapping("exibeSetor")
	public String exibe(Long id, Model model) {
		model.addAttribute("setor", dao.buscaPorId(id));
		return "setor/exibe";
	}

	@RequestMapping("editaSetor")
	public String edita(Long id, Model model) {
		model.addAttribute("setor", dao.buscaPorId(id));
		return "setor/edita";
	}

	@RequestMapping("alteraSetor")
	public String altera(@Valid Setor setor, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:editaSetor?id=" + setor.getId();
		}

		dao.altera(setor);
		return "redirect:listaSetores";
	}

	@RequestMapping("relatorioSetor")
	public void relatorio(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		try {

			String nomeArquivo = request.getServletContext().getRealPath("/resources/relatorio/setores.jasper");
			Map<String, Object> parametros = new HashMap<String, Object>();
			Connection connection = new ConnectionFactory().getConnection();

			// Pego o usuário da sessão
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

			parametros.put("imagem_logo",
					request.getServletContext().getRealPath("/resources/imagens/relatorio_setores.png"));
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
