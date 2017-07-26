package com.govpro.config.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.StopWatch;

import com.govpro.config.GovproConstant;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

public class AsyncSpringLiquibase extends SpringLiquibase{
	
	private final Logger log=LoggerFactory.getLogger(AsyncSpringLiquibase.class);
	
	private final TaskExecutor taskExecutor;
	
	private final Environment env;
	
	public AsyncSpringLiquibase(@Qualifier("taskExecutor") TaskExecutor taskExecutor, Environment env) {
		this.taskExecutor=taskExecutor;
		this.env=env;
	}
	
	

	@Override
	public void afterPropertiesSet() throws LiquibaseException {
		if(env.acceptsProfiles(GovproConstant.PROFILE_DEVELOPMENT)){
			taskExecutor.execute(()->{
				try{
					log.warn("Starting liquibase asynchronously");
					initDb();
				}catch(LiquibaseException e){
					log.error("Liquibase cannot start correctly:{}",e,e.getMessage());
				}
			});
		}else{
			log.debug("Starting liquibase");
		}
		super.afterPropertiesSet();
	}
	
	protected void initDb() throws LiquibaseException{
		StopWatch watch=new StopWatch();
		watch.start();
		super.afterPropertiesSet();
		watch.stop();
		log.debug("Liquibase has updated your database in {} ms", watch.getTotalTimeMillis());
        if (watch.getTotalTimeMillis() > 5_000) {
            log.warn("Warning, Liquibase took more than 5 seconds to start up!");
        }
	}
}
