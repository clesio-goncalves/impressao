package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.UsuarioPC;

@Repository
public class UsuarioPCDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(UsuarioPC usuarioPC) {
		if (buscaUsuario(usuarioPC).size() == 0) {
			manager.persist(usuarioPC);
		}
	}

	public List<UsuarioPC> lista() {
		return manager.createQuery("select u from UsuarioPC as u", UsuarioPC.class).getResultList();
	}

	public List<UsuarioPC> buscaUsuario(UsuarioPC usuarioPC) {
		return manager
				.createQuery("select u from UsuarioPC as u where u.nome = :nome and u.estacao = :estacao", UsuarioPC.class)
				.setParameter("nome", usuarioPC.getNome()).setParameter("estacao", usuarioPC.getEstacao()).getResultList();
	}

}
