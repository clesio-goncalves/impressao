package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Impressao;

@Repository
public class ImpressaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Impressao impressao) {
		manager.persist(impressao);
	}

	public List<Impressao> lista() {
		return manager.createQuery("select i from Impressao as i").getResultList();
	}

	public List<Impressao> buscaImpressao(Impressao impressao) {
		return manager
				.createQuery(
						"select i from Impressao as i where i.impressora.nome = :nomeImpressora and i.estacao.nome = :nomeEstacao",
						Impressao.class)
				.setParameter("nomeImpressora", impressao.getImpressora().getNome())
				.setParameter("nomeEstacao", impressao.getEstacao().getNome()).getResultList();
	}

	public Long qntImpressao() {
		return manager.createQuery("select SUM(i.qnt_copias * i.qnt_paginas) as total_impressao from Impressao as i",
				Long.class).getSingleResult();
	}

}
