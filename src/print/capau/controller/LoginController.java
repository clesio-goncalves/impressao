package print.capau.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import print.capau.dao.SetorDao;
import print.capau.dao.UsuarioDao;
import print.capau.modelo.Setor;
import print.capau.modelo.Usuario;

@Transactional
@Controller
public class LoginController {

	@Autowired
	UsuarioDao dao;

	@Autowired
	SetorDao dao_setor;

	@RequestMapping("login")
	public String login() {
		return "login/login";
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if (dao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", dao.buscaUsuario(usuario));
			return "redirect:index";
		}
		return "redirect:login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

}