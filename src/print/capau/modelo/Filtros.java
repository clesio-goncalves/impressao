package print.capau.modelo;

public class Filtros {

	private String data_inicial;
	private String data_final;
	private String nome_impressora;
	private String nome_estacao;
	private String minimo_impressoes;
	private String nome_usuario;

	public String getData_inicial() {
		return data_inicial;
	}

	public void setData_inicial(String data_inicial) {
		this.data_inicial = data_inicial;
	}

	public String getData_final() {
		return data_final;
	}

	public void setData_final(String data_final) {
		this.data_final = data_final;
	}

	public String getNome_impressora() {
		return nome_impressora;
	}

	public void setNome_impressora(String nome_impressora) {
		this.nome_impressora = nome_impressora;
	}

	public String getNome_estacao() {
		return nome_estacao;
	}

	public void setNome_estacao(String nome_estacao) {
		this.nome_estacao = nome_estacao;
	}

	public String getMinimo_impressoes() {
		return minimo_impressoes;
	}

	public void setMinimo_impressoes(String minimo_impressoes) {
		this.minimo_impressoes = minimo_impressoes;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

}
