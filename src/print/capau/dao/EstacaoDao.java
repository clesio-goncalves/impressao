package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Estacao;

@Repository
public class EstacaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Estacao estacao) {
		if (buscaIdPeloNome(estacao.getNome()).size() == 0) {
			manager.persist(estacao);
		}
	}

	public List<Estacao> lista() {
		return manager.createQuery("select e from Estacao as e").getResultList();
	}

	public List<Estacao> buscaIdPeloNome(String nome) {
		return manager.createQuery("select e from Estacao as e where e.nome = :nome", Estacao.class)
				.setParameter("nome", nome).getResultList();
	}

}
