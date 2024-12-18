//package com.lms.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.lang.NonNull;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.resource.PathResourceResolver;
//
//import java.io.IOException;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    // Serve static resources and handle requests to index.html
//    @Override
//    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/")  // Ensure static files are in this folder
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver() {
//                    @Override
//                    protected Resource getResource(@NonNull String resourcePath,
//                                                    @NonNull Resource location) throws IOException {
//                        Resource requestedResource = location.createRelative(resourcePath);
//                        return requestedResource.exists() && requestedResource.isReadable()
//                                ? requestedResource
//                                : new ClassPathResource("/static/index.html");  // Return index.html for SPA
//                    }
//                });
//    }
//
//    // CORS configuration
//    @Override
//    public void addCorsMappings(@NonNull CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
//                .allowedOrigins("*") // Update to match your frontend URL
//                .allowedHeaders("*")
//                .exposedHeaders("Authorization")
//                .allowCredentials(true); // Allow credentials like cookies or authentication headers
//    }
//}
