package print.capau.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import print.capau.dao.ConfiguracaoDao;
import print.capau.dao.EstacaoDao;
import print.capau.dao.ImpressaoDao;
import print.capau.dao.ImpressoraDao;
import print.capau.dao.UsuarioPCDao;
import print.capau.modelo.Configuracao;
import print.capau.modelo.Estacao;
import print.capau.modelo.Impressao;
import print.capau.modelo.Impressora;
import print.capau.modelo.Usuario;
import print.capau.modelo.UsuarioPC;
import print.capau.relatorio.GeradorRelatorio;

@Transactional
@Controller
public class ImpressaoController {

	private List<Impressao> lista_impressoes;
	private List<String> linhas_arquivo;
	private Impressao impressao;
	private Impressora impressora;
	private Estacao estacao;
	private UsuarioPC usuarioPC;
	private boolean boleano;
	private Long estacao_id;
	private String data_inicial, data_final;
	private Configuracao configuracao = new Configuracao();
	private int indice, ultima_linha, tmp_ultima_linha;
	private int total_linhas;
	private String nome_arquivo;
	private Path arquivo;

	@Autowired
	ImpressaoDao dao;

	@Autowired
	UsuarioPCDao dao_usuarioPC;

	@Autowired
	ImpressoraDao dao_impressora;

	@Autowired
	EstacaoDao dao_estacao;

	@Autowired
	ConfiguracaoDao dao_configuracao;

	@RequestMapping("listaImpressoes")
	public String relatorio(Long id, Model model) {

		// Verifica se o diretorio de logs ja está cadastrado
		if (dao_configuracao.qntRegistro().intValue() != 0) {
			// Verifica se o id da impressora foi informado na listagem de impressões
			if (id == null) {
				// atualizar();
				atualizacao_geral();
				lista_impressoes = dao.lista();
				model.addAttribute("impressoes", lista_impressoes);
			} else {
				lista_impressoes = dao.buscaImpressaoPorImpressora(id);
				model.addAttribute("impressoes", lista_impressoes);
			}

			model.addAttribute("impressoras", dao_impressora.lista());
			return "impressao/lista";
		} else {
			return "configuracao/logs/novo";
		}

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
			impressao.getUsuarioPC().setNome(request.getParameter("nome_usuario"));
		}

		lista_impressoes = dao.buscaImpressao(impressao); // Sempre possuira a instancia mais recente da lista de
															// impressoes
		model.addAttribute("impressoes", lista_impressoes);
		model.addAttribute("impressoras", dao_impressora.lista());
		return "impressao/tabela";
	}

	public void atualizacao_geral() {

		try {

			// Lista de arquivos ordenados por nome
			List<Path> diretorio = Files.list(Paths.get(diretorioLogs())).sorted().collect(Collectors.toList());

			// Pega o ultimo arquivo do banco de dados, caso já esteja inserido
			if (configuracao.getUltimo_arquivo() != null) {
				// Descobre o indice do ultimo arquivo atualizado
				for (int i = 0; i < diretorio.size(); i++) {
					if (diretorio.get(i).toString().endsWith(configuracao.getUltimo_arquivo())) {
						indice = i;
						ultima_linha = configuracao.getUltima_linha();
					}
				}
			} else {
				indice = 0;
				ultima_linha = 2;
			}

			// System.out.println("Indice do ultimo arquivo: " + indice);

			// Percorre todos os arquivos desde o último arquivo atualizado
			for (int i = indice; i < diretorio.size(); i++) {

				linhas_arquivo = new ArrayList<String>();
				impressao = new Impressao();

				arquivo = diretorio.get(i);

				linhas_arquivo = Files.readAllLines(arquivo, StandardCharsets.ISO_8859_1);

				nome_arquivo = arquivo.getFileName().toString();
				total_linhas = linhas_arquivo.size();

				// System.out.println("Arquivo " + i + ": " + nome_arquivo + " -> Total de
				// linhas: " + total_linhas);

				// System.out.println("Última linha: " + ultima_linha);

				// System.out.println(
				// "------------------------------------------------------------------------------------------------");

				if (ultima_linha < total_linhas) {
					// Percorre todos as linhas do arquivo a partir da ultima linha
					for (int j = ultima_linha; j < total_linhas; j++) {

						// System.out.println((j + 1) + " - " + linhas_arquivo.get(j));

						String[] dados = linhas_arquivo.get(j).split(",");

						// Impressora
						impressora = new Impressora();
						impressora.setNome(dados[4]);
						dao_impressora.adiciona(impressora);

						// Estação
						estacao = new Estacao();
						estacao.setNome(dados[6]);
						dao_estacao.adiciona(estacao);

						// Usuario
						usuarioPC = new UsuarioPC();
						usuarioPC.setNome(dados[1]);

						estacao_id = dao_estacao.buscaIdPeloNome(dados[6]).get(0).getId();
						usuarioPC.getEstacao().setId(estacao_id);

						dao_usuarioPC.adiciona(usuarioPC);

						// Impressao
						impressao = new Impressao();

						impressao.getImpressora().setId(dao_impressora.buscaIdPeloNome(dados[4]).get(0).getId());
						impressao.getEstacao().setId(estacao_id);
						impressao.getUsuarioPC().setId(dao_usuarioPC.buscaUsuario(usuarioPC).get(0).getId());

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

						ultima_linha = j + 1;
					}
				}

				tmp_ultima_linha = ultima_linha;
				ultima_linha = 2;

				// System.out.println(
				// "=======================================================================================================");
			}

			// Altera o ultimo arquivo e ultima linha no banco de dados
			configuracao.setUltimo_arquivo(nome_arquivo);
			configuracao.setUltima_linha(tmp_ultima_linha);
			dao_configuracao.altera(configuracao);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("relatorioImpressao")
	public void relatorio(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String nomeRelatorio = "Relatório de Impressões";
		String nomeArquivo = request.getServletContext().getRealPath("/resources/relatorio/impressoes.jasper");
		Map<String, Object> parametros = new HashMap<String, Object>();
		JRBeanCollectionDataSource relatorio = new JRBeanCollectionDataSource(lista_impressoes);

		// Pego o usuário da sessão
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

		// Parâmetros do relatório
		parametros.put("imagem_logo",
				request.getServletContext().getRealPath("/resources/imagens/relatorio_impressoes.png"));
		parametros.put("nome_usuario", usuario.getNome());
		parametros.put("login_usuario", usuario.getUsuario());

		GeradorRelatorio gerador = new GeradorRelatorio(nomeRelatorio, nomeArquivo, parametros, relatorio);
		gerador.geraPDFParaOutputStream(response);

	}

	public String diretorioLogs() {
		configuracao = dao_configuracao.buscaConfiguracao(); // Recebe o nome do diretorio
		return configuracao.getDiretorio() + "/csv/daily";
	}

}