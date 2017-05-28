package br.com.casadocodigo.loja.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.transaction.TransactionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//classe de configuração
@Configuration
//classe de transação de banco
@EnableTransactionManagement
public class JPAConfiguration {

	// metodos de configurações precisam ser conhecidos pelo spring, entao sao
	// anotados como bean
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		// gerencia os adaptadores de banco de dados
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("casadocodigo");
		dataSource.setPassword("casadocodigo");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		factoryBean.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "false");

		// toda vez que mudar o modelo hibernate mudara o banco
		props.setProperty("hibernate.hbm2ddl.auto", "update");

		factoryBean.setJpaProperties(props);

		// pacotes de entidades
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");

		return factoryBean;
	}
	
	//gerenciador de tarnsaçoes
	@Bean
	public JpaTransactionManager  TransactionManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}

}
