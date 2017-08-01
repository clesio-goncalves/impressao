package print.capau.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Impressora {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String nome;

	@ManyToOne
	private Setor setor = new Setor();

	@Transient
	private Long total_impressao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Long getTotal_impressao() {
		return total_impressao;
	}

	public void setTotal_impressao(Long total_impressao) {
		this.total_impressao = total_impressao;
	}

}
