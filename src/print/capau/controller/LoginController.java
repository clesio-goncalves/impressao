package print.capau.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import print.capau.dao.LoginDao;
import print.capau.dao.SetorDao;
import print.capau.modelo.Login;
import print.capau.modelo.Perfil;

@Transactional
@Controller
public class LoginController {

	@Autowired
	LoginDao dao;

	@Autowired
	SetorDao dao_setor;

	@RequestMapping("login")
	public String login() {
		return "login/login";
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Login login, HttpSession session) {
		if (dao.existeLogin(login)) {
			session.setAttribute("usuarioLogado", login);
			return "redirect:index";
		}
		return "redirect:login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

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

		return "login/novo";
	}

	@RequestMapping("adicionaUsuario")
	public String adiciona(@Valid Login login, BindingResult result) {

		if (result.hasErrors() || login.comparaSenhas() == false) {
			return "redirect:novoUsuario";
		}

		// Adiciona no banco de dados
		dao.adiciona(login);
		return "redirect:listaUsuarios";
	}

	@RequestMapping("listaUsuarios")
	public String lista(Model model) {
		model.addAttribute("logins", dao.lista());
		return "login/lista";
	}

	@RequestMapping("removeUsuario")
	public String remove(Login login) {
		dao.remove(login);
		return "redirect:listaUsuarios";
	}

	@RequestMapping("exibeUsuario")
	public String exibe(Long id, Model model) {
		model.addAttribute("login", dao.buscaPorId(id));
		return "login/exibe";
	}

	@RequestMapping("editaUsuario")
	public String edita(Long id, Model model) {

		// Testa se há setores cadastrados
		if (dao_setor.lista().size() == 0) {
			return "redirect:novoSetor";
		} else {
			// Adiciona os setores a lista
			model.addAttribute("login", dao.buscaPorId(id));
			model.addAttribute("perfis", Perfil.values());
			model.addAttribute("setores", dao_setor.lista());
			return "login/edita";
		}
	}

	@RequestMapping("alteraUsuario")
	public String altera(@Valid Login login, BindingResult result) {

		if (result.hasErrors() || login.comparaSenhas() == false) {
			return "redirect:editaUsuario?id=" + login.getId();
		}

		// Altera no banco
		dao.altera(login);
		return "redirect:listaUsuarios";

	}
}