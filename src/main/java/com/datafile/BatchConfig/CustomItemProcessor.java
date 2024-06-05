package com.datafile.BatchConfig;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.datafile.model.CasaRecord;


@Component
public class CustomItemProcessor implements ItemProcessor<CasaRecord, CasaRecord> {
             
	@Override
	public CasaRecord process(CasaRecord item) throws Exception {
		     
		System.out.println("-----------------------" +item);
		return item;
		

		
	}


}