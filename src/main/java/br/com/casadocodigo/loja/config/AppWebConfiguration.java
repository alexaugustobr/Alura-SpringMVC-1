package br.com.casadocodigo.loja.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

//configurador do servlet
@EnableWebMvc
//escaneia home controler e o torna um controller para injetar
@ComponentScan(basePackageClasses={HomeController.class,ProdutoDAO.class,FileSaver.class,CarrinhoCompras.class})
public class AppWebConfiguration {
	
	
	//acha arquivos em JSP na pasta prefix e com extensao jsp
	//Bean classe gerencia pelo spring
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver () {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");//oq vem antes
		resolver.setSuffix(".jsp");//sufixo pagina jsp
		//deixa o beans (como o carrinho) em forma de atributo disponivel na expression language JSP
		//resolver.setExposeContextBeansAsAttributes(true);
		//deixa apenas o carrinho visivel
		resolver.setExposedContextBeanNames("carrinhoCompras");
		
		return resolver;
	}
	
	@Bean
	//origem das mensagens de erro no form
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/WEB-INF/message");
		ms.setDefaultEncoding("UTF-8");
		ms.setCacheSeconds(1);
		return ms;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService(){
		DefaultFormattingConversionService cs = new DefaultFormattingConversionService();
		DateFormatterRegistrar dfr = new DateFormatterRegistrar();
		dfr.setFormatter(new DateFormatter("dd/MM/yyyy"));
		dfr.registerFormatters(cs);
		return cs;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
}
