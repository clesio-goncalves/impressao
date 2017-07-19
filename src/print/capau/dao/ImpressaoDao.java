package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Impressao;

@Repository
public class ImpressaoDao {

	private String sql, data_inicial, data_final;

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Impressao impressao) {
		manager.persist(impressao);
	}

	public List<Impressao> lista() {
		return manager.createQuery("select i from Impressao as i").getResultList();
	}

	public List<Impressao> buscaImpressao(Impressao impressao) {

		sql = "select i from Impressao as i";

		// Data inicial e data final
		data_inicial = impressao.formataData(impressao.getData_inicial(), "yyyy-MM-dd");
		data_final = impressao.formataData(impressao.getData_final(), "yyyy-MM-dd");

		sql = sql + " where i.data between '" + data_inicial + "' and '" + data_final + "'";

		// Impressora
		if (impressao.getImpressora().getNome() != null) {
			sql = sql + " and i.impressora.nome = '" + impressao.getImpressora().getNome() + "'";
		}

		// Estação
		if (impressao.getEstacao().getNome() != null) {
			sql = sql + " and i.estacao.nome = '" + impressao.getEstacao().getNome() + "'";
		}

		// Usuário
		if (impressao.getUsuario().getNome() != null) {
			sql = sql + " and i.usuario.nome = '" + impressao.getUsuario().getNome() + "'";
		}

		// Quantidade Minima de Impressoes
		if (impressao.getQnt_impressoes() != null) {
			sql = sql + " and (i.qnt_copias * i.qnt_paginas) >= " + impressao.getQnt_impressoes();
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
