package com.eureka.doumi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//使用Filter 处理跨域请求，即CORS（跨来源资源共享）。
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean corsFilter(){
        // 注册CORS过滤器
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        /**
         * CorsConfiguration配置类继承了WebMvcConfiugrationAdaper父类并且重写了addCorsMappings
         * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
         * allowedMethods：允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
         * allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
         * allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息，如："X-YAUTH-TOKEN"
         */
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin("http://127.0.0.1:8020");
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader("*");// 请求头
        config.addAllowedMethod("*");// 请求方法
        source.registerCorsConfiguration("/**",config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }
}
