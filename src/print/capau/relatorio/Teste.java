package print.capau.relatorio;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Teste {
	
	public static void main(String[] args) {
		
		String senha = new BCryptPasswordEncoder().encode("thalita");
		System.out.println(senha);
	}

}
