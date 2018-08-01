package print.capau.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Configuracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String diretorio;

	private String ultimo_arquivo;

	private Integer ultima_linha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public String getUltimo_arquivo() {
		return ultimo_arquivo;
	}

	public void setUltimo_arquivo(String ultimo_arquivo) {
		this.ultimo_arquivo = ultimo_arquivo;
	}

	public Integer getUltima_linha() {
		return ultima_linha;
	}

	public void setUltima_linha(Integer ultima_linha) {
		this.ultima_linha = ultima_linha;
	}

}
