package com.govpro.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.govpro.config.database.AsyncSpringLiquibase;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableJpaRepositories("com.govpro.repository")
@EnableJpaAuditing(auditorAwareRef="springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {

	private final Logger log=LoggerFactory.getLogger(DatabaseConfiguration.class);
	
	private final Environment env;
	
	public DatabaseConfiguration(Environment env){
		this.env=env;
	}
	
	@Bean(initMethod="start", destroyMethod="stop")
	@Profile(GovproConstant.PROFILE_DEVELOPMENT)
	public Server h2tcpServer() throws SQLException{
		return Server.createTcpServer("-tcp","-tcpAllowOthers");
		
	}
	
	@Bean
	public SpringLiquibase liquibase(@Qualifier("taskExecutor")TaskExecutor taskExecutor,
			DataSource dataSource,LiquibaseProperties liquibaseProperties){
		SpringLiquibase liquibase=new AsyncSpringLiquibase(taskExecutor, env);
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:config/liquibase/master.xml");
		liquibase.setContexts(liquibaseProperties.getContexts());
		liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
		liquibase.setDropFirst(liquibaseProperties.isDropFirst());
		if(env.acceptsProfiles(GovproConstant.PROFILE_NO_LIQUIBASE)){
			liquibase.setShouldRun(false);
		}else{
			liquibase.setShouldRun(liquibaseProperties.isEnabled());
			log.debug("Configuring liquibase database migration");
		}
		return liquibase;
	}
}
