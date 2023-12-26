package com.gxcy.config.security;

import com.gxcy.config.filter.CheckTokenFilter;
import com.gxcy.config.security.handler.AnonymousAuthenticationHandler;
import com.gxcy.config.security.handler.CustomerAccessDeniedHandler;
import com.gxcy.config.security.handler.LoginFailureHandler;
import com.gxcy.config.security.handler.LoginSuccessHandler;
import com.gxcy.config.security.service.CustomerUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
//开启权限注解控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomerUserDetailService customerUserDetailService;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;

    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Resource
    private CheckTokenFilter checkTokenFilter;

    /**
     * 配置认证处理
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());
    }

    /**
     * 注入加密处理类
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] paths = {"/css/**", "/js/**", "/images/**", "/swagger-ui.html",
                "/index.html", "favicon.ico", "/doc.html", "/webjars/**",
                "/swagger-resources/**", "/v2/api-docs/**", "/ws/**"};
        //登录前进行过滤
        http.formLogin()
                //.loginProcessingUrl("/api/sysUser/login")
                //设置登录验证成功或失败后的跳转地址
                .successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
                //禁用csrf防御机制
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(paths).permitAll()
                .antMatchers("/api/sysUser/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)
                .accessDeniedHandler(customerAccessDeniedHandler)
                .and().cors(); //开启跨域配置

        http.addFilterBefore(checkTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

}
