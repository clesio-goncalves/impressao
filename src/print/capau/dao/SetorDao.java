package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Setor;

@Repository
public class SetorDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Setor setor) {
		manager.persist(setor);
	}

	public void altera(Setor setor) {
		manager.merge(setor);
	}

	public List<Setor> lista() {
		return manager.createQuery("select s from Setor s", Setor.class).getResultList();
	}

	public Setor buscaPorId(Long id) {
		return manager.find(Setor.class, id);
	}

	public void remove(Setor setor) {
		Setor setorARemover = buscaPorId(setor.getId());
		manager.remove(setorARemover);
	}

}
