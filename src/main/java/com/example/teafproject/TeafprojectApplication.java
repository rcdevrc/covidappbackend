package com.example.teafproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.teafproject.springbootmongodbsecurity.domain.Role;
import com.example.teafproject.springbootmongodbsecurity.repository.RoleRepository;



@SpringBootApplication
//@ComponentScan({"com.example.teafproject.springbootmongodbsecurity.controller.UploadController"})
 public class TeafprojectApplication {
//	public class TeafprojectApplication extends SpringBootServletInitializer {
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
//	{ 
//		return application.sources(TeafprojectApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(TeafprojectApplication.class, args);
	}

	   @Bean
	    CommandLineRunner init(RoleRepository roleRepository) {

	        return args -> {

	            Role adminRole = roleRepository.findByRole("ADMIN");
	            if (adminRole == null) {
	                Role newAdminRole = new Role();
	                newAdminRole.setRole("ADMIN");
	                roleRepository.save(newAdminRole);
	            }
	            
	            Role userRole = roleRepository.findByRole("USER");
	            if (userRole == null) {
	                Role newUserRole = new Role();
	                newUserRole.setRole("USER");
	                roleRepository.save(newUserRole);
	            }
	        };

	    }
	   
	   @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	   
	   @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**");
	            }
	        };
	    }
	   
}
