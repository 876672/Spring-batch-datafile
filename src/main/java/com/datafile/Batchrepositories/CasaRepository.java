package com.datafile.Batchrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datafile.model.CasaRecord;

public interface CasaRepository extends JpaRepository<CasaRecord, String>  {

}
