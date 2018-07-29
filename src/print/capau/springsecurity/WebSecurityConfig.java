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
			.antMatchers("/diretorioLogs").hasRole("ADMIN")
			.antMatchers("/adicionaDiretorioLogs").hasRole("ADMIN")
			.antMatchers("/alteraDiretorioLogs").hasRole("ADMIN")
			.antMatchers("/desativacaoIpv6").hasRole("ADMIN")
			.antMatchers("/novoSetor").hasRole("ADMIN")
			.antMatchers("/adicionaSetor").hasRole("ADMIN")
			.antMatchers("/removeSetor").hasRole("ADMIN")
			.antMatchers("/editaSetor").hasRole("ADMIN")
			.antMatchers("/alteraSetor").hasRole("ADMIN")
			.antMatchers("/novoUsuario").hasRole("ADMIN")
			.antMatchers("/adicionaUsuario").hasRole("ADMIN")
			.antMatchers("/removeUsuario").hasRole("ADMIN")
			.antMatchers("/editaUsuario").hasRole("ADMIN")
			.antMatchers("/alteraUsuario").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
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
