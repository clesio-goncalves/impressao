package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Permissao;

@Repository
public class PermissaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Permissao permissao) {
		manager.persist(permissao);
	}

	public void altera(Permissao permissao) {
		manager.merge(permissao);
	}

	public List<Permissao> lista() {
		return manager.createQuery("select p from Permissao p", Permissao.class).getResultList();
	}

	public Permissao buscaPorId(Long id) {
		return manager.find(Permissao.class, id);
	}

	public void remove(Permissao permissao) {
		Permissao permissaoARemover = buscaPorId(permissao.getId());
		manager.remove(permissaoARemover);
	}

}
