package print.capau.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Configuracao;

@Repository
public class ConfiguracaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Configuracao configuracao) {
		manager.persist(configuracao);
	}

	public void altera(Configuracao configuracao) {
		manager.merge(configuracao);
	}

	public Long qntRegistro() {
		return manager.createQuery("select count(c) from Configuracao c", Long.class).getSingleResult();
	}

	public Configuracao buscaDiretorio() {
		return manager.createQuery("select c from Configuracao c", Configuracao.class).getSingleResult();
	}

}
