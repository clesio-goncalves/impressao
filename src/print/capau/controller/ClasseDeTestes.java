package print.capau.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ClasseDeTestes {

	static int ultima_linha = 20;
	static int indice = -1;
	static String ultima_atualizacao = "2017-07-20";
	static String nome = "papercut-print-log-" + ultima_atualizacao + ".csv";
	static List<Path> diretorio;
	static int total_linhas;
	static List<String> linhas_arquivo;
	static String nome_arquivo;
	static Path arquivo;

	public static void main(String[] args) {

		long inicio = System.currentTimeMillis();

		String string = "/home/clesio/eclipse-workspace/impressao/logs" + "/csv/daily";

		try {

			// Lista de arquivos ordenados por nome
			diretorio = Files.list(Paths.get(string)).sorted().collect(Collectors.toList());

			// Descobre o indice do ultimo arquivo atualizado
			for (int i = 0; i < diretorio.size(); i++) {
				if (diretorio.get(i).toString().endsWith(nome)) {
					indice = i;
				}
			}

			System.out.println("Indice do ultimo arquivo: " + indice);

			// Percorre todos os arquivos desde o último arquivo atualizado
			for (int i = indice; i < diretorio.size(); i++) {

				arquivo = diretorio.get(i);

				linhas_arquivo = Files.readAllLines(arquivo, StandardCharsets.ISO_8859_1);

				nome_arquivo = arquivo.getFileName().toString();
				total_linhas = linhas_arquivo.size();

				System.out.println("Arquivo " + i + ": " + nome_arquivo + " -> Total de linhas: " + total_linhas);

				System.out.println(
						"------------------------------------------------------------------------------------------------");

				if (ultima_linha < total_linhas) {
					// Percorre todos as linhas do arquivo a partir da ultima linha
					for (int j = ultima_linha; j < linhas_arquivo.size(); j++) {
						System.out.println((j + 1) + " - " + linhas_arquivo.get(j));
					}
				}

				ultima_linha = 2;

				System.out.println(
						"=======================================================================================================");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		long fim = System.currentTimeMillis();
		System.out.println((fim - inicio) + " milisegundos.");

	}

	private static void atualizar() {

		try {
			linhas_arquivo = Files.readAllLines(arquivo, StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (ultima_linha < total_linhas) {
			// Percorre todos as linhas do arquivo a partir da segunda linha
			for (int j = ultima_linha; j < linhas_arquivo.size(); j++) {
				System.out.println((j + 1) + " - " + linhas_arquivo.get(j));
			}
		}

	}
}