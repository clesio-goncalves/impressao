package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Login;

@Repository
public class LoginDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Login login) {
		manager.persist(login);
	}

	public void altera(Login login) {
		manager.merge(login);
	}

	public List<Login> lista() {
		return manager.createQuery("select l from Login l").getResultList();
	}

	public Login buscaPorId(Long id) {
		return manager.find(Login.class, id);
	}

	public void remove(Login login) {
		Login loginARemover = buscaPorId(login.getId());
		manager.remove(loginARemover);
	}

	public boolean existeLogin(Login login) {

		if (login == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo!");
		}

		Query query = manager.createQuery(
				"select l from Login l where l.usuario = :usuarioLogin and l.senha = :senhaLogin and l.ativo =:ativoLogin");
		query.setParameter("usuarioLogin", login.getUsuario());
		query.setParameter("senhaLogin", login.getSenha());
		query.setParameter("ativoLogin", true);

		List<Login> lista = query.getResultList();

		boolean resultado = (lista.size() == 1) ? true : false;

		return resultado;
	}

}
