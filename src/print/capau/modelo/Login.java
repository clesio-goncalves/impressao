package print.capau.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Login {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 5, max = 50)
	@Column(unique = true)
	private String usuario;

	@NotNull
	@Size(min = 5, max = 50)
	private String senha;

	@Transient
	private String repetir_senha;
	// O atributo repetir_senha não será persistido no banco

	private boolean ativo;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	@NotNull
	@ManyToOne
	private Setor setor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRepetir_senha() {
		return repetir_senha;
	}

	public void setRepetir_senha(String repetir_senha) {
		this.repetir_senha = repetir_senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public boolean comparaSenhas() {
		if (this.senha.equals(this.repetir_senha)) {
			return true;
		} else {
			return false;
		}
	}

}
