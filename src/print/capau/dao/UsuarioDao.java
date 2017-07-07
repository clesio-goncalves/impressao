package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Usuario;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Usuario usuario) {
		if (buscaUsuario(usuario).size() == 0) {
			manager.persist(usuario);
		}
	}

	public List<Usuario> lista() {
		return manager.createQuery("select u from Usuario as u").getResultList();
	}

	public List<Usuario> buscaUsuario(Usuario usuario) {
		return manager
				.createQuery("select u from Usuario as u where u.nome = :nome and u.estacao = :estacao", Usuario.class)
				.setParameter("nome", usuario.getNome()).setParameter("estacao", usuario.getEstacao()).getResultList();
	}

}
