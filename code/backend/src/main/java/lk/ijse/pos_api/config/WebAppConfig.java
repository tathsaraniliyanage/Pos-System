package lk.ijse.pos_api.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.MultipartConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Configuration
@EnableWebMvc
@ComponentScan("lk.ijse.pos_api.controller")
@MultipartConfig(
        maxFileSize = 5000000,
        maxRequestSize = 5000000,
        fileSizeThreshold = 5000000
)
public class WebAppConfig  {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
