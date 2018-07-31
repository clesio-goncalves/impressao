package print.capau.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import print.capau.dao.UsuarioDao;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDao userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
			.antMatchers("/diretorioLogs").hasRole("Administrador")
			.antMatchers("/adicionaDiretorioLogs").hasRole("Administrador")
			.antMatchers("/alteraDiretorioLogs").hasRole("Administrador")
			.antMatchers("/desativacaoIpv6").hasRole("Administrador")
			.antMatchers("/novoSetor").hasRole("Administrador")
			.antMatchers("/adicionaSetor").hasRole("Administrador")
			.antMatchers("/removeSetor").hasRole("Administrador")
			.antMatchers("/editaSetor").hasRole("Administrador")
			.antMatchers("/alteraSetor").hasRole("Administrador")
			.antMatchers("/novoUsuario").hasRole("Administrador")
			.antMatchers("/adicionaUsuario").hasRole("Administrador")
			.antMatchers("/removeUsuario").hasRole("Administrador")
			.antMatchers("/editaUsuario").hasRole("Administrador")
			.antMatchers("/alteraUsuario").hasRole("Administrador")
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
			.rememberMe()
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

}
