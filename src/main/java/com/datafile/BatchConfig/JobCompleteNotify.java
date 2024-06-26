package com.datafile.BatchConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;


@Component
public class JobCompleteNotify implements JobExecutionListener {
	
    private Logger logger= LoggerFactory.getLogger(JobCompleteNotify.class);
    
    
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
    	 logger.info(" ----------------Just job Started -----------------");
        
    }
 
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("--------------Total work done----------------");
            
            for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
                logger.info("Step Name: {}", stepExecution.getStepName());
                logger.info("Read Count: {}", stepExecution.getReadCount());
                logger.info("Write Count: {}", stepExecution.getWriteCount());
                
            }
        }
    }
}
