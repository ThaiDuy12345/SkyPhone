package com.nhathanh;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nhathanh.model.TaiKhoan;
import com.nhathanh.service.SessionService;
import com.nhathanh.service.TaiKhoanService;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	TaiKhoanService taikhoanService;
	@Autowired
	SessionService session;
	// Mã hóa mật khẩu
	@Bean
	public static BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Cung cấp nguồn dữ liệu đăng nhập SkyPhone
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				TaiKhoan user = taikhoanService.findById(username);
				String password = pe.encode(user.getPassword());
				String vaiTro = String.valueOf(user.getVai_tro());
				session.set("user", user);
				return User.withUsername(username).password(password).roles(vaiTro).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "Not found!");
			}
		});
	}

	// Cho phép truy xuất REST API từ bên ngoài (doman khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	// Phân quyền sử dụng và hình thức đăng nhập
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF,CORS
		http.csrf().disable();

//		Phân quyền
		http.authorizeRequests()
		.antMatchers("/skyPhoneAdmin").authenticated()
		.antMatchers("/skyPhoneEmploy").authenticated()
		.antMatchers("/skyPhoneAdmin/**").authenticated()
		.antMatchers("/skyPhoneEmploy/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("0")
		.antMatchers("/SkyPhone/index").hasRole("0").anyRequest().permitAll();

		http.formLogin()
				// Khi gặp địa chỉ URL này
				.loginPage("/security/login/form")
				// Đưa vào địa chỉ này để xử lý URL
				.loginProcessingUrl("/admin/login")
				// False để trở lại trang người dùng vừa yêu cầu
				// True sẽ tiếp tục quay trở lại địa chỉ URL của trang đăng nhập
				.defaultSuccessUrl("/security/login/success", false)
				.failureUrl("/security/login/error");

		// Check remember
		http.rememberMe().tokenValiditySeconds(86400);

		http.exceptionHandling().accessDeniedPage("/security/unauthoried");// error

		// Đăng xuất
		http.logout().logoutUrl("/skyPhone/logout").logoutSuccessUrl("/security/logoff/success");
	}
}
