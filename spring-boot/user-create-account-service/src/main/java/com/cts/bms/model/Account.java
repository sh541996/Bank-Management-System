package com.cts.bms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	@Pattern(regexp="[1-9][0-9]{9}", message="account no should be 10 digit long")
	private String accountNo;
	
	@Column(nullable=false)
	private String ifcCode;
	
	@Column(nullable=false)
	private String bankName;
	
	
	@Column(nullable=false)
	private String micrCode;
	
	@Column
	private String pan;
	
	public Account() {
		super();
	}


	public Account(@Pattern(regexp = "[1-9][0-9]{9}", message = "account no should be 10 digit long") String accountNo,
			String ifcCode, String bankName, String micrCode, String pan) {
		super();
		this.accountNo = accountNo;
		this.ifcCode = ifcCode;
		this.bankName = bankName;
		this.micrCode = micrCode;
		this.pan = pan;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public String getIfcCode() {
		return ifcCode;
	}


	public void setIfcCode(String ifcCode) {
		this.ifcCode = ifcCode;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getMicrCode() {
		return micrCode;
	}


	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ifcCode == null) ? 0 : ifcCode.hashCode());
		result = prime * result + ((micrCode == null) ? 0 : micrCode.hashCode());
		result = prime * result + ((pan == null) ? 0 : pan.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ifcCode == null) {
			if (other.ifcCode != null)
				return false;
		} else if (!ifcCode.equals(other.ifcCode))
			return false;
		if (micrCode == null) {
			if (other.micrCode != null)
				return false;
		} else if (!micrCode.equals(other.micrCode))
			return false;
		if (pan == null) {
			if (other.pan != null)
				return false;
		} else if (!pan.equals(other.pan))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNo=" + accountNo + ", ifcCode=" + ifcCode + ", bankName=" + bankName
				+ ", micrCode=" + micrCode + ", pan=" + pan + "]";
	}
	
	
	
	

}
