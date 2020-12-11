package com.example.config;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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



//	バリデーションのエラー時に出すメッセージを変更するファイル

	@Override
	public Validator getValidator() {
	var validator = new LocalValidatorFactoryBean();
	validator.setValidationMessageSource(messageSource());
	return validator;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
	var messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("messages");
	return messageSource;
	}


//	データベース関連の設定を書いていく

	@Bean
	public DataSource dataSource() throws Exception{
		InitialContext ctx = new InitialContext();
//		個々のアドレスを変更することで、接続先を変更できます。
		var ctxLU = ctx.lookup("java:comp/env/jdbc/practice_db");
		return (DataSource)ctx;
	}






}
