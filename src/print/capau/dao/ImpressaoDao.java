package print.capau.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import print.capau.modelo.Impressao;

@Repository
public class ImpressaoDao {

	private String sql_select, sql_condicao = "", data_inicial, data_final;

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Impressao impressao) {
		manager.persist(impressao);
	}

	public List<Impressao> lista() {
		return manager.createQuery("select i from Impressao as i order by i.data desc").getResultList();
	}

	public List<Impressao> desativacaoIpv6() {
		return manager.createQuery("select i from Impressao as i where i.estacao.nome like '[%]' order by i.data desc")
				.getResultList();
	}

	public List<Impressao> buscaImpressao(Impressao impressao) {

		sql_select = "select i from Impressao as i";

		// Data inicial e data final
		data_inicial = impressao.formataData(impressao.getData_inicial(), "yyyy-MM-dd");
		data_final = impressao.formataData(impressao.getData_final(), "yyyy-MM-dd");

		sql_condicao = sql_condicao + " where i.data between '" + data_inicial + "' and '" + data_final + "'";

		// Impressora
		if (impressao.getImpressora().getNome() != null) {
			sql_condicao = sql_condicao + " and i.impressora.nome = '" + impressao.getImpressora().getNome() + "'";
		}

		// Estação
		if (impressao.getEstacao().getNome() != null) {
			sql_condicao = sql_condicao + " and i.estacao.nome = '" + impressao.getEstacao().getNome() + "'";
		}

		// Usuário
		if (impressao.getUsuario().getNome() != null) {
			sql_condicao = sql_condicao + " and i.usuario.nome = '" + impressao.getUsuario().getNome() + "'";
		}

		// Quantidade Minima de Impressoes
		if (impressao.getQnt_impressoes() != null) {
			sql_condicao = sql_condicao + " and (i.qnt_copias * i.qnt_paginas) >= " + impressao.getQnt_impressoes();
		}

		sql_condicao = sql_condicao + " order by i.data desc";

		System.out.println("-----------------------------------------------------");
		System.out.println("SQL: " + sql_select + sql_condicao);
		System.out.println("-----------------------------------------------------");

		return manager.createQuery(sql_select + sql_condicao, Impressao.class).getResultList();
	}

	public Long qntImpressao() {
		return manager.createQuery(
				"select SUM(i.qnt_copias * i.qnt_paginas) as total_impressao from Impressao as i " + sql_condicao,
				Long.class).getSingleResult();
	}

}
