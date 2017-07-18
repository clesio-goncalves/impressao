package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Impressao;

@Repository
public class ImpressaoDao {

	private String sql;
	private boolean where;

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Impressao impressao) {
		manager.persist(impressao);
	}

	public List<Impressao> lista() {
		return manager.createQuery("select i from Impressao as i").getResultList();
	}

	public List<Impressao> buscaImpressao(Impressao impressao) {
		// return manager
		// .createQuery(
		// "select i from Impressao as i where i.impressora.nome = :nomeImpressora and
		// i.estacao.nome = :nomeEstacao",
		// Impressao.class)
		// .setParameter("nomeImpressora", impressao.getImpressora().getNome())
		// .setParameter("nomeEstacao",
		// impressao.getEstacao().getNome()).getResultList();

		sql = "select i from Impressao as i";
		where = false;

		// Data inicial
		if (impressao.getData() != null) {
			if (where == false) {
				sql = sql + " where";
				where = true;
			}

			sql = sql + " i.data = '" + impressao.getData() + "'";
		}

		// Impressora
		if (impressao.getImpressora().getNome() != null) {
			if (where == false) {
				sql = sql + " where";
				where = true;
			} else {
				sql = sql + " and";
			}

			sql = sql + " i.impressora.nome = '" + impressao.getImpressora().getNome() + "'";
		}

		// Estação
		if (impressao.getEstacao().getNome() != null) {
			if (where == false) {
				sql = sql + " where";
				where = true;
			} else {
				sql = sql + " and";
			}

			sql = sql + " i.estacao.nome = '" + impressao.getEstacao().getNome() + "'";
		}

		// Usuário
		if (impressao.getUsuario().getNome() != null) {
			if (where == false) {
				sql = sql + " where";
				where = true;
			} else {
				sql = sql + " and";
			}

			sql = sql + " i.usuario.nome = '" + impressao.getUsuario().getNome() + "'";
		}

		System.out.println("-----------------------------------------------------");
		System.out.println("SQL: " + sql);
		System.out.println("-----------------------------------------------------");

		return manager.createQuery(sql, Impressao.class).getResultList();
	}

	public Long qntImpressao() {
		return manager.createQuery("select SUM(i.qnt_copias * i.qnt_paginas) as total_impressao from Impressao as i",
				Long.class).getSingleResult();
	}

}
