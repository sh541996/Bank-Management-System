package com.cts.bms;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.bms.exception.FundNameInvalid;
import com.cts.bms.exception.PanOrAccountInvalid;
import com.cts.bms.feign.AccountClient;
import com.cts.bms.model.Investment;
import com.cts.bms.model.MutualFund;
import com.cts.bms.repository.InvestmentRepository;
import com.cts.bms.repository.MutualFundRepository;
import com.cts.bms.service.InvestmentService;
import com.cts.bms.service.InvestmentServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserInvestmentServiceApplicationTests {

	@Mock
	AccountClient accountClient;
	
	@Mock
	InvestmentRepository investmentRepository;
	
	@Mock
	MutualFundRepository mutualFundRepository;
	
	@InjectMocks
	InvestmentService investmentService = new InvestmentServiceImpl();
	

	@Test
	public void summaryServiceTest() throws Exception {
	 
		List<Investment> investmentList_1 = new ArrayList();
		investmentList_1.add(new Investment(1l, "eee", "1234567874", 99, "2020-08-17T17:48:49.822356", "IHBSR5420B", 3291447l));
		investmentList_1.add(new Investment(2l, "aaa", "1234589874", 78, "2020-08-16T22:06:31.977072", "IHBSR5420B", 375144l));
		investmentList_1.add(new Investment(3l, "ccc", "1234537874", 94, "2020-04-16T22:06:31.977072", "IHBSR5420B", 3569144l));
		investmentList_1.add(new Investment(4l, "eee", "9034589874", 90, "2080-01-16T22:06:31.977072", "IHBSR5420B", 37144l));
		investmentList_1.add(new Investment(5l, "ddd", "1234963674", 56, "2020-09-16T22:06:31.977072", "IHBSR5420B", 375198l));
		when(investmentRepository.findAllByPan("IHBSR5420B")).thenReturn(investmentList_1);
		
		List<Investment> investment_2 = investmentService.summaryService("IHBSR5420B");
		
		assertEquals("eee", investment_2.get(0).getFundName());
		assertEquals("aaa", investment_2.get(4).getFundName());
				
	}
	
	@Test
	public void summaryByFundIdServiceTest() throws Exception { 
		
		Investment investment_1 = new Investment(5l, "ddd", "1234963674", 56, "2020-09-16T22:06:31.977072", "IHBSR5420B", 375198l);
		when(investmentRepository.findByFundId(375198l)).thenReturn(investment_1);
		Investment investment_2 = investmentService.summaryByFundIdService("IHBSR5420B", 375198l);
		assertEquals(investment_1, investment_2);
	}
	
}
