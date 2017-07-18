package print.capau.importacao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImportaDados {

	public static void main(String[] args) {

		final String local = "/eclipse-workspace/impressao/logs/csv/monthly/papercut-print-log-2016-06.csv";

		Path caminho = Paths.get(System.getProperty("user.home"), local);

		List<String> linhas = new ArrayList<String>();
		try {
			linhas = Files.readAllLines(caminho, StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] dados = null;

		for (int i = 2; i < linhas.size(); i++) {
			// System.out.println(linhas.get(i));
			dados = linhas.get(i).split(",");

			// inserir no banco de dados
			for (String string : dados) {
				System.out.println(string);
			}

			System.out.println("---------------------------------------");
		}
	}

}
