package com.cts.bms.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.exception.FundNameInvalid;
import com.cts.bms.exception.NoInvestment;
import com.cts.bms.exception.PanOrAccountInvalid;
import com.cts.bms.exception.PanOrFundIdInvalid;
import com.cts.bms.feign.AccountClient;
import com.cts.bms.model.FundId;
import com.cts.bms.model.Investment;
import com.cts.bms.model.MutualFund;
import com.cts.bms.repository.FundIdRepository;
import com.cts.bms.repository.InvestmentRepository;
import com.cts.bms.repository.MutualFundRepository;

/**
 * 
 * @author shubhamkumar
 *
 */
@Service
public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	AccountClient accountClient;

	@Autowired
	InvestmentRepository investmentRepository;

	@Autowired
	MutualFundRepository mutualFundRepository;

	@Autowired
	FundIdRepository fundIdRepository;

	private Logger logger = LoggerFactory.getLogger(InvestmentServiceImpl.class);

	/**
	 * This method is for saving investment details
	 * 
	 * @Param investment
	 * @Return Investment
	 */
	@Override
	public Investment saveInvestmentService(Investment investment) throws PanOrAccountInvalid, FundNameInvalid {

		// loging
		String methodName = "saveInvestmentService()";
		logger.info(methodName + " called");

		List<MutualFund> mutualFundList = mutualFundRepository.findAll();
		System.out.println(mutualFundList);
		// to check is given fund name by user is available in db or not by checking in
		// mutual_Fund table
		if (mutualFundList.stream().filter(item -> item.getFundName().equals(investment.getFundName().toUpperCase()))
				.count() == 1) {

			// to check weather the pan and account provided by user exist in db or not
			// using account microservice
			if (accountClient.verifyAccount(investment.getPan(), investment.getAccount())) {

				// to create a timestamp
				LocalDateTime stamp = LocalDateTime.now();
				String timeStamp = stamp.toString();
				investment.setTimeStamp(timeStamp);

				// to generate a sequential fundId
				FundId fundId = fundIdRepository.getOne(1l);
				investment.setFundId(fundId.getFundId());
				long newFundId = fundId.getFundId();
				fundIdRepository.save(new FundId(1l, ++newFundId));

				return investmentRepository.save(investment);
			}

			// if pan or account is not registered in db
			throw new PanOrAccountInvalid("please provide valid pan or accountNo");
		}

		// if fund name provided by user is not exist
		throw new FundNameInvalid("please provide valid mutual fund name");
	}

	// to get the details of all investment of a particular user using pan
	@Override
	public List<Investment> summaryService(@Valid String pan) throws NoInvestment {

		// loging
		String methodName = "summaryService()";
		logger.info(methodName + " called");

		List<Investment> listInvestment = investmentRepository.findAllByPan(pan);

		if (listInvestment != null) {
			// sort the list in descending order
			Collections.sort(listInvestment,
					(investment1, investment2) -> investment2.getFundName().compareTo(investment1.getFundName()));
			return listInvestment;
		}

		// if no investment is done by user
		else
			throw new NoInvestment("no investment using pan " + pan);
	}

	// to get all detail of a particular investment using fundId and pan
	@Override
	public Investment summaryByFundIdService(String pan, long fundId) throws PanOrFundIdInvalid {

		// loging
		String methodName = "summaryByFundIdService()";
		logger.info(methodName + " called");

		Investment investment = investmentRepository.findByFundId(fundId);
		if (investment.getPan().equalsIgnoreCase(pan))
			return investment;

		// if there is no investment using given fundId or wrong pan is given
		else
			throw new PanOrFundIdInvalid("please provide valid pan and fundId");
	}

}
