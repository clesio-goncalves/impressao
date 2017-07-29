package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Usuario;

@Repository
public class UsuarioDao {

	private List<Usuario> lista;

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Usuario usuario) {
		manager.persist(usuario);
	}

	public void altera(Usuario usuario) {
		manager.merge(usuario);
	}

	public List<Usuario> lista() {
		return manager.createQuery("select u from Usuario u").getResultList();
	}

	public Usuario buscaPorId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public Usuario buscaUsuario(Usuario usuario) {
		return manager.createQuery("select u from Usuario u where u.usuario = :usuario", Usuario.class)
				.setParameter("usuario", usuario.getUsuario()).getSingleResult();
	}

	public void remove(Usuario usuario) {
		Usuario usuarioARemover = buscaPorId(usuario.getId());
		manager.remove(usuarioARemover);
	}

	public boolean existeUsuario(Usuario usuario) {

		if (usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo!");
		}

		Query query = manager.createQuery(
				"select u from Usuario u where u.usuario = :usuario and u.senha = :senha and u.ativo =:ativo");
		query.setParameter("usuario", usuario.getUsuario());
		query.setParameter("senha", usuario.getSenha());
		query.setParameter("ativo", true);

		lista = query.getResultList();

		boolean resultado = (lista.size() == 1) ? true : false;

		return resultado;
	}

}
