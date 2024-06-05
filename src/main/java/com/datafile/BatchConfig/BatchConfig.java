package com.datafile.BatchConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import com.datafile.Batchrepositories.CasaRepository;
import com.datafile.model.CasaRecord;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Configuration
public class BatchConfig {
	
	private final CasaRepository casaRepository;
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	
	
	
	
	
	@Bean
	public Step step(ItemProcessor<CasaRecord,CasaRecord> processor) {
		System.out.println("step--------------------------");

		return (new StepBuilder("setp",jobRepository)
				.<CasaRecord,CasaRecord>chunk(10,transactionManager)
				.reader(itemReader())
                .writer(itemWriter())
                .processor(processor)
                .allowStartIfComplete(true)) 
				.build();
	}
             
               
	


	@Bean
	public FlatFileItemReader<CasaRecord> itemReader() {
		System.out.println("reader--------------------------");
		return new FlatFileItemReaderBuilder<CasaRecord>()
				.name("itemReader")
				.resource(new ClassPathResource("cdb322w.txt"))
				.lineTokenizer(fixedLengthTokenizer())
				.targetType(CasaRecord.class)
				.strict(false)
				.build();
				
	}
	
	
	

       


	
	 @Bean
	 public FixedLengthTokenizer fixedLengthTokenizer() {
	     FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
       tokenizer.setColumns(new Range[] {
       new Range(1, 19), new Range(20,25 ), new Range(26, 39), new Range(40, 53), 
       new Range(54, 57), new Range(58, 77), new Range(78, 88), new Range(89,89), 
       new Range(90, 104), new Range(105, 107), new Range(108, 125), new Range(126, 126), 
       new Range(127, 141), new Range(142, 181), new Range(182, 185), new Range(186, 189), 
       new Range(190, 196), new Range(197, 199), new Range(200, 214), new Range(215, 229), 
       new Range(230, 244), new Range(245, 259), new Range(260, 274), new Range(275, 284), 
       new Range(285, 294), new Range(295, 304), new Range(305, 314), new Range(315, 324), 
       new Range(325, 327), new Range(328, 328), new Range(329, 347),new Range(348, 348), 
       new Range(349, 351), new Range(352, 353), new Range(354, 555), new Range(556, 580),
       new Range(581,836 ),new Range(837, 876), new Range(877, 916)
   });     
     tokenizer.setNames(new String[] {
       "accountNumber", "runningSequenceNumber", "txnDateTime", "authorisedCurrentAC", 
       "txnCode", "txnDescription", "referenceNumber", "txnSign", "txnAmount", 
       "txnServicingBranch", "eftNumber", "statementEndingBalSign", 
       "statementEndingBalance", "paymentDetail", "accountCurrency", 
       "mappedSwiftCode", "julianDate", "sourceBranch", "reservedAmount1", 
       "reservedAmount2", "reservedAmount3", "reservedAmount4", "reservedAmount5", 
       "remarks1", "remarks2", "remarks3", "remarks4", "remarks5", "accountBranch", 
       "accountStatus", "cifNumber", "classCode", "lob", "sectorCode", 
       "productCode", "filler1", "filler2", "filler3", "filler4"
      });	     
       tokenizer.setStrict(false);
	     return tokenizer;
	 }
	 

	 public ItemProcessor<CasaRecord, CasaRecord> itemProcessor() {
	 return new CustomItemProcessor();
		
	}
	 
	 
	 
	@Bean
	public RepositoryItemWriter<CasaRecord>  itemWriter() {
		System.out.println("itemWriter--------------------------");
		return new RepositoryItemWriterBuilder<CasaRecord>()
				.methodName("save")
				.repository(casaRepository)
				.build();
		
	}

	
	
	@Bean
	public Job job(Step step,JobCompleteNotify listener) {
		System.out.println("job--------------------------");
		return new JobBuilder("job2",jobRepository)
				.start(step)
				.listener(listener)
				.build();
		
				}
	
	
}
