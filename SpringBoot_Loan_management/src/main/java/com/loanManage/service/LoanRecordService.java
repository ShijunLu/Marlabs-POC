package com.loanManage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loanManage.entity.LoanRecord;
import com.loanManage.repository.LoanRecordRepository;

@Service
@Transactional
public class LoanRecordService {
	
	@Autowired
	private LoanRecordRepository repo;
	
	public List<LoanRecord> listAll(){
		return repo.findAll();
	}
	
	
	public void save(LoanRecord loanRecord) {
		System.out.println("into save processing");
		repo.save(loanRecord);
	}
	
	public LoanRecord get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
