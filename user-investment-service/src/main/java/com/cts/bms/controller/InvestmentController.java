package com.cts.bms.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bms.model.Investment;
import com.cts.bms.repository.MutualFundRepository;
import com.cts.bms.service.InvestmentService;

@RestController
public class InvestmentController {
	
	@Autowired
	InvestmentService investmentService;
	
	@Autowired
	MutualFundRepository mutualFundRepository;
	
	private Logger logger = LoggerFactory.getLogger(InvestmentController.class);

	// to save the investment details in db
	@RequestMapping(value = "/invest", method = RequestMethod.POST)
	public ResponseEntity<?> saveInvestment(@Valid @RequestBody Investment investment) throws Exception {
		
		// loging
		String methodName = "saveInvestment()";
		logger.info(methodName+" called");
				
		return ResponseEntity.ok(investmentService.saveInvestmentService(investment));
	}
	
	// to get the summary of investment using pan
	@RequestMapping(value = "/summary/{pan}", method = RequestMethod.GET)
	public ResponseEntity<?> summary(@PathVariable String pan) throws Exception {
		
		// loging
		String methodName = "summary()";
		logger.info(methodName+" called");
		
		return ResponseEntity.ok(investmentService.summaryService(pan));
	}
	
	// to get the investment details of a particular investment using fundId and pan
	@RequestMapping(value="/summary/{pan}/{fundId}")
	public ResponseEntity<?> summaryByFundId(@PathVariable String pan, @PathVariable long fundId ) throws Exception {
		
		// loging
		String methodName = "summaryByFundId()";
		logger.info(methodName+" called");
		
		return ResponseEntity.ok(investmentService.summaryByFundIdService(pan, fundId));
		
	}	
}
