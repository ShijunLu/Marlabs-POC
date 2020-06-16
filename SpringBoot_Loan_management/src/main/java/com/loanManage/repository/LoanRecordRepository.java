package com.loanManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanManage.entity.LoanRecord;


public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {
	
}
