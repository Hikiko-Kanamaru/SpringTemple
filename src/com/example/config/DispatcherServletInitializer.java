package com.example.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {




		return new Class[] {ApplicationConfig.class};
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] {"/"};
	}

//	文字化け表示フィルター
	@Override
	protected Filter[] getServletFilters() {
		var filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return new Filter[] {filter};

	}


}
