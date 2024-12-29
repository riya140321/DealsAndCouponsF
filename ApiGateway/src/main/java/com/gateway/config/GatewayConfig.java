package com.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig implements WebMvcConfigurer {

	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**") // Apply to all routes
	                .allowedOrigins("http://localhost:5173") // Allow requests from React app
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                .allowedHeaders("*")
	                .allowCredentials(true); // Allow credentials like cookies
	 }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		/*
		 * return builder.routes() .route("ADMINSERVICE", r -> r.path("/admin/**")
		 * .filters(f -> f.filter(jwtAuthenticationFilter.apply( new
		 * JwtAuthenticationFilter.Config("ADMIN")))) .uri("lb://ADMINSERVICE"))
		 * .route("USER-SERVICE", r -> r.path("/user/**") .filters(f ->
		 * f.filter(jwtAuthenticationFilter.apply( new
		 * JwtAuthenticationFilter.Config("USER")))) .uri("lb://USER-SERVICE"))
		 * 
		 * .build();
		 */
    	return builder.routes()
                .route("AUTHENTICATIONSERVICE", r -> r.path("/auth/**")
                    .uri("http://localhost:9098/auth"))
                .route("USER-SERVICE", r -> r.path("/user/**")
                    .uri("http://localhost:7191/user"))
                // Add more routes as necessary
                .build();
    	
    }
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("http://localhost:7191/auth/register");
        config.addAllowedOrigin( "http://localhost:9098/auth/register");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Enable OPTIONS preflight requests
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    @Bean
    public GlobalFilter corsPreflightFilter() {
        return (exchange, chain) -> {
            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:7191/auth/register");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:9098/auth/register");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "*");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Credentials", "true");
            }
            return chain.filter(exchange);
       
        };
    }
    
    @Bean
    public GlobalFilter corsGlobalFilter() {
        return (exchange, chain) -> {
            // Add CORS headers to the response
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:7191/auth/register");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:9098/auth/register");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "*");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Credentials", "true");
            
            // Proceed with the request chain
            return chain.filter(exchange);
        };
    }



    
    
    
}
