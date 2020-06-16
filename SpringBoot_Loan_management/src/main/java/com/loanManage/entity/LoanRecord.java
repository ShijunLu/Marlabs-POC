package com.loanManage.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class LoanRecord {
	private Long id;
	private String name;
	private String type;
	private int term;
	private int amount;
	private float apr;
	private boolean secured;
	private String start_date;
	private String description;
	private String financial_infor;
	private String status;
	
	public LoanRecord() {
		
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getApr() {
		return apr;
	}

	public void setApr(float apr) {
		this.apr = apr;
	}

	public boolean isSecured() {
		return secured;
	}

	public void setSecured(boolean secured) {
		this.secured = secured;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinancial_infor() {
		return financial_infor;
	}

	public void setFinancial_infor(String financial_infor) {
		this.financial_infor = financial_infor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
