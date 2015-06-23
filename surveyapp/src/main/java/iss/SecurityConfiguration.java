package iss;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(1)
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter*/{
//	@Autowired
//	DataSource dataSource;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		http.authorizeRequests().anyRequest()
//				.authenticated().and()
//				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//				.csrf().csrfTokenRepository(csrfTokenRepository()).and().httpBasic();
//		//http.httpBasic().and().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login");
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth)
//			throws Exception {
//		// auth.inMemoryAuthentication().withUser("aan").password("aan").roles("ADMIN");
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.usersByUsernameQuery(
//						"SELECT email as username, password, enabled FROM users WHERE email=?")
//				.authoritiesByUsernameQuery(
//						"SELECT email as username, role FROM users WHERE email=?");
//	}
//
//	private CsrfTokenRepository csrfTokenRepository() {
//		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//		repository.setHeaderName("X-XSRF-TOKEN");
//		return repository;
//	}
//
//	class CsrfHeaderFilter extends OncePerRequestFilter {
//		@Override
//		protected void doFilterInternal(HttpServletRequest request,
//				HttpServletResponse response, FilterChain filterChain)
//				throws ServletException, IOException {
//			CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
//					.getName());
//			if (csrf != null) {
//				Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//				String token = csrf.getToken();
//				if (cookie == null || token != null
//						&& !token.equals(cookie.getValue())) {
//					cookie = new Cookie("XSRF-TOKEN", token);
//					cookie.setPath("/");
//					response.addCookie(cookie);
//				}
//			}
//			filterChain.doFilter(request, response);
//		}
//
//	}
}
