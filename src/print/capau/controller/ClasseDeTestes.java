package print.capau.controller;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClasseDeTestes {

	public static void main(String[] args) {

		String string = "/home/clesio/eclipse-workspace/impressao/logs" + "/csv/daily";

		try {

			// Pesquisar sobre métodos da classe files

			List<Path> teste = Files.list(Paths.get(string)).collect(Collectors.toList());

			for (Path string2 : teste) {
				Files.readAllLines(string2, StandardCharsets.ISO_8859_1).forEach(System.out::println);
				System.out.println("===================================================================");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
