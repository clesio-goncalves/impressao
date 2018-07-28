package print.capau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import print.capau.dao.SetorDao;
import print.capau.dao.UsuarioDao;
import print.capau.modelo.Perfil;
import print.capau.modelo.Usuario;
import print.capau.relatorio.GeradorRelatorio;

@Transactional
@Controller
public class UsuarioController {

	private List<Usuario> lista_usuarios;

	@Autowired
	UsuarioDao dao;

	@Autowired
	SetorDao dao_setor;

	@RequestMapping("novoUsuario")
	public String novoUsuario(Model model) {

		// Testa se há setores cadastrados
		if (dao_setor.lista().size() == 0) {
			return "redirect:novoSetor";
		} else {
			// Adiciona os setores a lista
			model.addAttribute("perfis", Perfil.values());
			model.addAttribute("setores", dao_setor.lista());
		}

		return "usuario/novo";
	}

	@RequestMapping("adicionaUsuario")
	public String adiciona(@Valid Usuario usuario, BindingResult result) {

		if (result.hasErrors() || usuario.comparaSenhas() == false) {
			return "redirect:novoUsuario";
		}

		// aplica o hash a senha fornecida
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		// Adiciona no banco de dados
		dao.adiciona(usuario);
		return "redirect:listaUsuarios";
	}

	@RequestMapping("listaUsuarios")
	public String lista(Model model) {
		lista_usuarios = dao.lista();
		model.addAttribute("usuarios", lista_usuarios);
		return "usuario/lista";
	}

	@RequestMapping("removeUsuario")
	public String remove(Usuario usuario) {
		dao.remove(usuario);
		return "redirect:listaUsuarios";
	}

	@RequestMapping("exibeUsuario")
	public String exibe(Long id, Model model) {
		model.addAttribute("usuario", dao.buscaPorId(id));
		return "usuario/exibe";
	}

	@RequestMapping("editaUsuario")
	public String edita(Long id, Model model) {

		// Testa se há setores cadastrados
		if (dao_setor.lista().size() == 0) {
			return "redirect:novoSetor";
		} else {
			// Adiciona os setores a lista
			model.addAttribute("usuario", dao.buscaPorId(id));
			model.addAttribute("perfis", Perfil.values());
			model.addAttribute("setores", dao_setor.lista());
			return "usuario/edita";
		}
	}

	@RequestMapping("alteraUsuario")
	public String altera(@Valid Usuario usuario, BindingResult result) {

		if (result.hasErrors() || usuario.comparaSenhas() == false) {
			return "redirect:editaUsuario?id=" + usuario.getId();
		}
		
		// aplica o hash a senha fornecida
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		// Altera no banco
		dao.altera(usuario);
		return "redirect:listaUsuarios";

	}

	@RequestMapping("relatorioUsuario")
	public void relatorio(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String nomeRelatorio = "Relatório de Usuários";
		String nomeArquivo = request.getServletContext().getRealPath("/resources/relatorio/usuarios.jasper");
		Map<String, Object> parametros = new HashMap<String, Object>();
		JRBeanCollectionDataSource relatorio = new JRBeanCollectionDataSource(lista_usuarios);

		// Pego o usuário da sessão
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

		parametros.put("imagem_logo",
				request.getServletContext().getRealPath("/resources/imagens/relatorio_usuarios.png"));
		parametros.put("nome_usuario", usuario.getNome());
		parametros.put("login_usuario", usuario.getUsuario());

		GeradorRelatorio gerador = new GeradorRelatorio(nomeRelatorio, nomeArquivo, parametros, relatorio);
		gerador.geraPDFParaOutputStream(response);

	}

}
