package kr.co.hellopet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	protected void configure(HttpSecurity http) throws Exception{
		// 접근권한
		http.authorizeRequests().antMatchers("/").permitAll();
		// member 에 접근하는 사용자가 로그인을 한 사용자라면 index 로 이동
		/*
		http.authorizeRequests().antMatchers("/my/**").hasAnyRole("1");
		http.authorizeRequests().antMatchers("/community/**").permitAll();
		http.authorizeRequests().antMatchers("/cs/**").permitAll();
		http.authorizeRequests().antMatchers("/disease/**").permitAll();
		http.authorizeRequests().antMatchers("/lists/**").permitAll();
		http.authorizeRequests().antMatchers("/message/**").permitAll();
		http.authorizeRequests().antMatchers("/search/**").permitAll();
		*/
	/*
	 * http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("7");
	 */
		// 추후 수정 예정 2023/03/08
		
		// 위조 방지
		http.csrf().disable();
		
		// 로그인 설정
		http.formLogin().loginPage("/member/login").defaultSuccessUrl("/index").failureUrl("/member/login?success=100")
		.usernameParameter("uid").passwordParameter("pass");
		
		// 자동로그인 설정
		http.rememberMe()
		.key("UniqueAndSecret").rememberMeParameter("remember-me")
		.tokenValiditySeconds(60*60*24).userDetailsService(userService);
		
		
		// 로그아웃 설정
		http.logout().invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
		.logoutSuccessUrl("/index");
       
	}
	
	@Autowired
	private SecurityUserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// 로그인 인증 처리 서비스, 암호화 방식 설정
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
