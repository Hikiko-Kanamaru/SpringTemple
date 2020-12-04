package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.example")
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {

//	ViewResolverの設定
//	普通のプログラムとは違う
	@Bean
	public ViewResolver viewResolver() {

		var viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;

	}


//	静的ファイルの有効化をする
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		フロントコントローラーにさばいてもらいたくないものを登録する
	registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	registry.addResourceHandler("/js/**").addResourceLocations("/js/");

	}


}
