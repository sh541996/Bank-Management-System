package com.cts.bms.service;

import java.util.List;

import javax.validation.Valid;

import com.cts.bms.model.Investment;



public interface InvestmentService {

	public Investment saveInvestmentService(Investment investment) throws Exception;

	public List<Investment> summaryService(@Valid String pan) throws Exception;

	public Investment summaryByFundIdService(String pan, long fundId) throws Exception;

	
}
