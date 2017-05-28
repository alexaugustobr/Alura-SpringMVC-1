package br.com.casadocodigo.loja.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.propertyeditors.CharacterEditor;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//main servlet que cuida das requisiçoes
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppWebConfiguration.class,JPAConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		return new Filter[] {cef};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		//configuraçao do path do envio do arquivo
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
