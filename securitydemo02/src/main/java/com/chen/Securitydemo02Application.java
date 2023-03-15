package com.chen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication

@MapperScan("com.chen.dao")
public class Securitydemo02Application {

    public static void main(String[] args) {
        SpringApplication.run(Securitydemo02Application.class, args);
    }
    /**@EnableGlobalMethodSecurity(securedEnabled = true)
     * @Secured 用户具有某个角色可以访问方法，注意要加ROLE_
     *
     * @EnableGlobalMethodSecurity(prePostEnabled = true)
     * @PreAuthoroize  方法之前进行校验
     *
     * @EnableGlobalMethodSecurity(prePostEnabled = true)
     * @PostAuthorize 方法之后进行校验
     *
     * @PostFilter  方法返回数据过滤
     * @PreFilter   传入方法的参数过滤
     */

}
