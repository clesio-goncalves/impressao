package print.capau.modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Impressao {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ManyToOne
	private Estacao estacao = new Estacao();

	@NotNull
	@ManyToOne
	private Impressora impressora = new Impressora();

	@NotNull
	@ManyToOne
	private Usuario usuario = new Usuario();

	@NotNull
	private String documento;

	@NotNull
	private Integer qnt_paginas;

	@NotNull
	private Integer qnt_copias;

	@NotNull
	private Boolean duplex;

	@NotNull
	private Boolean escala_cinza;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar data;

	@Transient
	private Calendar data_inicial;

	@Transient
	private Calendar data_final;

	@Transient
	private Integer qnt_impressoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	public Impressora getImpressora() {
		return impressora;
	}

	public void setImpressora(Impressora impressora) {
		this.impressora = impressora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Integer getQnt_paginas() {
		return qnt_paginas;
	}

	public void setQnt_paginas(Integer qnt_paginas) {
		this.qnt_paginas = qnt_paginas;
	}

	public Integer getQnt_copias() {
		return qnt_copias;
	}

	public void setQnt_copias(Integer qnt_copias) {
		this.qnt_copias = qnt_copias;
	}

	public Boolean getDuplex() {
		return duplex;
	}

	public void setDuplex(Boolean duplex) {
		this.duplex = duplex;
	}

	public Boolean getEscala_cinza() {
		return escala_cinza;
	}

	public void setEscala_cinza(Boolean escala_cinza) {
		this.escala_cinza = escala_cinza;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getData_inicial() {
		return data_inicial;
	}

	public void setData_inicial(Calendar data_inicial) {
		this.data_inicial = data_inicial;
	}

	public Calendar getData_final() {
		return data_final;
	}

	public void setData_final(Calendar data_final) {
		this.data_final = data_final;
	}

	public Integer getQnt_impressoes() {
		return qnt_impressoes;
	}

	public void setQnt_impressoes(Integer qnt_impressoes) {
		this.qnt_impressoes = qnt_impressoes;
	}

	public String formataData(Calendar calendar, String formato) {
		SimpleDateFormat fmt = new SimpleDateFormat(formato);
		Date data = calendar.getTime();
		return fmt.format(data);
	}

	public List<String> importarDados() {
		final String local = "/eclipse-workspace/impressao/logs/csv/monthly/papercut-print-log-2016-06.csv";

		Path caminho = Paths.get(System.getProperty("user.home"), local);

		try {
			return Files.readAllLines(caminho, StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
