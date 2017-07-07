package print.capau.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		model.addAttribute("impressoras", dao_impressora.lista());
		return "impressao/lista";
	}

	@RequestMapping(value = "filtrarImpressoes", method = RequestMethod.POST)
	public String filtrar(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		// Impressao
		impressao = new Impressao();

		// Impressora
		// Se a impressora for diferente de qualquer
		if (!request.getParameter("nome_impressora").equals("Qualquer")) {
			impressao.getImpressora().setNome(request.getParameter("nome_impressora"));
		}

		// Estação
		impressao.getEstacao().setNome(request.getParameter("nome_estacao"));

		model.addAttribute("impressoes", dao.buscaImpressao(impressao));
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
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			try {
				Date data = fmt.parse(dados[0]);
				c.setTime(data);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			impressao.setData(c);

			// Adiciona a impressora
			dao.adiciona(impressao);

			System.out.println("---------------------------------------");
		}
	}

}
