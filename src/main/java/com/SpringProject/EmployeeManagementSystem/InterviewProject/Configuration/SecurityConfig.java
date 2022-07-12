package com.SpringProject.EmployeeManagementSystem.InterviewProject.Configuration;

import com.SpringProject.EmployeeManagementSystem.InterviewProject.Security.EmployeeSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeSecurity employeeSecurity;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .antMatchers("/employee/**/add").hasRole("MANAGER")
                .antMatchers("/employee/all").hasRole("MANAGER")
                .antMatchers("/employee/get/**").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/employee/**/update/**").hasRole("MANAGER")
                .antMatchers("/employee/delete/**").hasRole("MANAGER")
                .antMatchers("/asset/**/add").hasRole("MANAGER")
                .antMatchers("/asset/all").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/asset/get/**").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/asset/**/update/**").hasRole("MANAGER")
                .antMatchers("/asset/delete/**").hasRole("MANAGER")
                .antMatchers("/organisation/add").hasRole("MANAGER")
                .antMatchers("/organisation/all").hasRole("MANAGER")
                .antMatchers("/organisation/get/**").hasRole("MANAGER")
                .antMatchers("/organisation/update/**").hasRole("MANAGER")
                .anyRequest().authenticated().and().httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.employeeSecurity).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}