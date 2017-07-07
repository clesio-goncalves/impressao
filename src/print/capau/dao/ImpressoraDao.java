package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Impressora;

@Repository
public class ImpressoraDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Impressora impressora) {
		if (buscaIdPeloNome(impressora.getNome()).size() == 0) {
			manager.persist(impressora);
		}
	}

	public void altera(Impressora impressora) {
		manager.merge(impressora);
	}

	public Impressora buscaPorId(Long id) {
		return manager.find(Impressora.class, id);
	}

	public List<Impressora> lista() {
		return manager.createQuery("select i from Impressora as i").getResultList();
	}

	public List<Impressora> buscaIdPeloNome(String nome) {
		return manager.createQuery("select i from Impressora as i where i.nome = :nome", Impressora.class)
				.setParameter("nome", nome).getResultList();
	}

	public Long qntImpressao(Impressora impressora) {
		return manager.createQuery(
				"select SUM(i.qnt_copias * i.qnt_paginas) as total_impressao from Impressao as i where i.impressora = :impressora",
				Long.class).setParameter("impressora", impressora).getSingleResult();
	}

}
