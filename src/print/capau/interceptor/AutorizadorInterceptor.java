package print.capau.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		// permite passar sem estar logado
		String uri = request.getRequestURI();
		if (uri.endsWith("login") || uri.endsWith("efetuaLogin") || uri.contains("resources")) {
			return true;
		}

		// Verifica se há usuário logado
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}

		response.sendRedirect("login");
		return false;
	}

}