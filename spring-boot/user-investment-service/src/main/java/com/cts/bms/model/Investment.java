package com.cts.bms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table
public class Investment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String fundName;
	
	@Column
	private String account;
	
	@Column
	@Min(value=10, message="amount should not less than 10")
	@Max(value=99, message="amount should not greater than 99")
	private int amount;
	
	@Column
	private String timeStamp;
	
	@Column
	private String pan;
	
	@Column
	private long fundId;
	
	

	public Investment() {
		super();
	}
	
	public Investment(Long id,String fundName, String account,
			@Min(value = 10, message = "amount should not less than 10") @Max(value = 99, message = "amount should not greater than 99") int amount,
			String timeStamp, String pan, long fundId) {
		super();
		this.id = id;
		this.fundName = fundName;
		this.account = account;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.pan = pan;
		this.fundId = fundId;
	}
	
	public Investment(String fundName, String account,
			@Min(value = 10, message = "amount should not less than 10") @Max(value = 99, message = "amount should not greater than 99") int amount,
			String pan) {
		super();
		this.fundName = fundName;
		this.account = account;
		this.amount = amount;
		this.pan = pan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public long getFundId() {
		return fundId;
	}

	public void setFundId(long fundId) {
		this.fundId = fundId;
	}

	@Override
	public String toString() {
		return "Investment [id=" + id + ", fundName=" + fundName + ", account=" + account + ", amount=" + amount
				+ ", timeStamp=" + timeStamp + ", pan=" + pan + ", fundId=" + fundId + "]";
	}
	
	
	

}
