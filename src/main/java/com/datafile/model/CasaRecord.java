package com.datafile.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "casa_record") 
public class CasaRecord {

	
	@Id
    private String accountNumber;


    private String runningSequenceNumber;

    private String txnDateTime;

    private String authorisedCurrentAC;

    private String txnCode;
    
    
    private String txnDescription;

    private String referenceNumber;

    private String txnSign; 

    private String txnAmount;

    private String txnServicingBranch;

    private String eftNumber;

    private String statementEndingBalSign;

    private String statementEndingBalance;

    private String paymentDetail;

    private String accountCurrency;

    private String mappedSwiftCode;

    private String julianDate;

    private String sourceBranch;

    private String reservedAmount1;

    private String reservedAmount2;

    private String reservedAmount3;

    private String reservedAmount4;

    private String reservedAmount5;

    private String remarks1;

    private String remarks2;

    private String remarks3;

    private String remarks4;

    private String remarks5;

    private String accountBranch;

    private String accountStatus;

    private String cifNumber;

    private String classCode;

    private String lob;

    private String sectorCode;

    private String productCode;

    private String filler1;

    private String filler2;

    private String filler3;
    
    private String filler4;


}
