package print.capau.modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	@Size(min = 5, max = 50)
	@Column(unique = true)
	private String usuario;

	@NotNull
	private String senha;

	@Transient
	private String repetir_senha;
	// O atributo repetir_senha não será persistido no banco

	private boolean ativo;

	@NotNull
	@ManyToOne
	private Setor setor;

	@NotNull
	@ManyToOne
	private Permissao permissao;

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

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<Permissao> permissoes = new HashSet<>();
		
		System.out.println("Permissao antes: " + this.permissao.getNome());

		this.permissao.setNome("ROLE_" + this.permissao.getNome());
		
		System.out.println("Permissao depois: " + this.permissao.getNome());

		permissoes.add(this.permissao);

		return permissoes;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.ativo;
	}

	public boolean comparaSenhas() {
		if (this.senha.equals(this.repetir_senha)) {
			return true;
		} else {
			return false;
		}
	}

}
