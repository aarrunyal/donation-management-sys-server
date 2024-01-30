package com.donationmanagementsystem.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
//@EnableWebMvc
public class CorsConfig  implements WebMvcConfigurer {
	@Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL);
            }
        };
    }

//   @Bean
//   CorsConfigurationSource corsConfigurationSource() {
//       CorsConfiguration configuration = new CorsConfiguration();
//       configuration.setAllowedOrigins(Arrays.asList("*"));
//       configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
//       configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
//       configuration.setAllowCredentials(true);
//       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//       source.registerCorsConfiguration("/**", configuration);
//       return source;
//   }

//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		// Allow all origins, you may want to restrict this in a production environments
//		config.addAllowedOrigin("*");
//		config.addAllowedMethod("*");
//		config.addAllowedHeader("*");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}

	
//	@Bean
//	   public WebMvcConfigurer corsConfigurer() {
//	       return new WebMvcConfigurerAdapter() {
//	           @Override
//	           public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/api/**")
//	                    .allowedOrigins("http://domain2.com")
//	                    .allowedMethods("*");
//	           }
//	       };
//	    }
	
//	@Override
//    public void addCorsMappings(CorsRegistry registry) {
//		  registry.addMapping("/**")
//          .allowedOrigins("http://localhost:3000")
//          .allowedMethods("PUT", "DELETE", "GET", "POST") //or allow all as you like
//          .allowedHeaders("Authorization", "Accept", "Content-Type")
//          .allowCredentials(false).maxAge(3600);
//    }
}
