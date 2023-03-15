package com.chen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 实现web登录自定义方式三
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().
                loginPage("./login.html")//登陆页面设置
                .loginProcessingUrl("/user/login")//登陆访问路径
                .defaultSuccessUrl("/test/hello").permitAll()//登陆成功之后跳转路径
                .and().authorizeRequests().
                antMatchers("/","test/hello","/user/login").permitAll()//设置那些路径不需要认证可以直接访问
                .anyRequest().authenticated()//对http所有的请求必须通过授权认证才可以访问。
                .and().csrf().disable();//关闭csrf的防护
        //.antMatchers("/test/index").hasAuthority("admin") 当前登录用户只有admin权限才能访问这个路径
        //.antMatchers("/test/index").hasAnyAuthority("admin,user") 只要有对应权限就能访问
        //.antMatchers("/test/index").hasRole("admin") 只要有对应角色就能访问 底层加上了Role_
        //http.exceptionHandling().accessDeniedPage("/forbidden.html")   自定义403没有访问权限的页面

        //退出登录
        //http.logout().logouturl("/logout").logoutSuccessUrl("/index").permitAll();

    }

    /**
     * 实现记住我
     * 1.要先建一张表
     * @Autowired
     * private DataSource datasource;
     * 2.修改配置类
     * @Bean
     * public PersistentTokenRepository persistenttokenreporsitory(){
     *     JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
     *     jdbcTokenRepository.setDataSource(dataSource);
     *     jdbcTokenRepository.setCreateTableOnStartup(true) 设置自动生成表 没走第一步要加
     *     return jdbcTokenRepository;
     * }
     * 3.该方法
     * .and().rememberMe().tokenReporsitory(persistenttokenreporsitory())
     * .tokenValiditySeconds(60).userDetailService(userDetailsService)
     * 前端的复选框必须要设置name为remember-me
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
