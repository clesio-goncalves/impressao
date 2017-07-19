package print.capau.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import print.capau.dao.EstacaoDao;
import print.capau.dao.ImpressaoDao;
import print.capau.dao.ImpressoraDao;
import print.capau.dao.UsuarioDao;
import print.capau.modelo.Estacao;
import print.capau.modelo.Impressao;
import print.capau.modelo.Impressora;
import print.capau.modelo.Usuario;

@Transactional
@Controller
public class ImpressaoController {

	private List<String> linhas;
	private Impressao impressao;
	private Impressora impressora;
	private Estacao estacao;
	private Usuario usuario;
	private boolean boleano;
	private Long estacao_id;
	private String data_inicial, data_final;

	@Autowired
	private ImpressaoDao dao;

	@Autowired
	private UsuarioDao dao_usuario;

	@Autowired
	private ImpressoraDao dao_impressora;

	@Autowired
	private EstacaoDao dao_estacao;

	@RequestMapping("listaImpressoes")
	public String relatorio(Model model) {
		// atualizar();
		model.addAttribute("impressoes", dao.lista());
		model.addAttribute("total_impressao", dao.qntImpressao());
		model.addAttribute("impressoras", dao_impressora.lista());
		return "impressao/lista";
	}

	@RequestMapping(value = "filtrarImpressoes", method = RequestMethod.POST)
	public String filtrar(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		// Impressao
		impressao = new Impressao();

		// Data inicial
		data_inicial = request.getParameter("data_inicial");

		// Se a data inicial não estiver sido informada, será atribuido 01/01/2000
		if (data_inicial.equals("")) {
			data_inicial = "01/01/2000";
		}

		// Converte a data inicial
		impressao.setData_inicial(impressao.converteStringParaCalendar(data_inicial, "dd/MM/yyyy"));

		// Data final
		data_final = request.getParameter("data_final");

		// Se a data final não estiver sido informada, sera atribuido a data atual do
		// servidor
		if (data_final.equals("")) {
			Calendar calendar = Calendar.getInstance();
			data_final = impressao.formataData(calendar, "dd/MM/yyyy");
		}

		// Converte a data final
		impressao.setData_final(impressao.converteStringParaCalendar(data_final, "dd/MM/yyyy"));

		// Impressora
		// Se a impressora for diferente de qualquer
		if (!request.getParameter("nome_impressora").equals("Qualquer")) {
			impressao.getImpressora().setNome(request.getParameter("nome_impressora"));
		}

		// Estação
		// Se for informada a estação
		if (!request.getParameter("nome_estacao").equals("")) {
			impressao.getEstacao().setNome(request.getParameter("nome_estacao"));
		}

		// Minimo de Impressões
		// Se for informada a quantidade minima de impressoes
		if (!request.getParameter("qnt_impressoes").equals("")) {
			impressao.setQnt_impressoes(Integer.parseInt(request.getParameter("qnt_impressoes")));
		}

		// Usuário
		// Se for informado o usuário
		if (!request.getParameter("nome_usuario").equals("")) {
			impressao.getUsuario().setNome(request.getParameter("nome_usuario"));
		}

		model.addAttribute("impressoes", dao.buscaImpressao(impressao));
		model.addAttribute("total_impressao", dao.qntImpressao());
		model.addAttribute("impressoras", dao_impressora.lista());
		return "impressao/tabela";
	}

	public void atualizar() {
		linhas = new ArrayList<String>();
		impressao = new Impressao();

		// Importa as linhas do arquivo
		linhas = impressao.importarDados();

		String[] dados = null;

		for (int i = 2; i < linhas.size(); i++) {
			dados = linhas.get(i).split(",");

			System.out.println(linhas.get(i));

			// Impressora
			impressora = new Impressora();
			impressora.setNome(dados[4]);
			dao_impressora.adiciona(impressora);

			// Estação
			estacao = new Estacao();
			estacao.setNome(dados[6]);
			dao_estacao.adiciona(estacao);

			// Usuario
			usuario = new Usuario();
			usuario.setNome(dados[1]);

			estacao_id = dao_estacao.buscaIdPeloNome(dados[6]).get(0).getId();
			usuario.getEstacao().setId(estacao_id);

			dao_usuario.adiciona(usuario);

			// Impressao
			impressao = new Impressao();

			impressao.getImpressora().setId(dao_impressora.buscaIdPeloNome(dados[4]).get(0).getId());
			impressao.getEstacao().setId(estacao_id);
			impressao.getUsuario().setId(dao_usuario.buscaUsuario(usuario).get(0).getId());

			impressao.setQnt_paginas(Integer.parseInt(dados[2]));
			impressao.setQnt_copias(Integer.parseInt(dados[3]));
			impressao.setDocumento(dados[5]);

			boleano = dados[11].equals("NOT DUPLEX") ? false : true;
			impressao.setDuplex(boleano);

			boleano = dados[12].equals("NOT GRAYSCALE") ? false : true;
			impressao.setEscala_cinza(boleano);

			// Data
			impressao.setData(impressao.converteStringParaCalendar(dados[0], "yyyy-MM-dd HH:mm:ss"));

			// Adiciona a impressora
			dao.adiciona(impressao);

			System.out.println("---------------------------------------");
		}
	}

}