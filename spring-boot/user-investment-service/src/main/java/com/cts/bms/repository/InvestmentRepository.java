package com.cts.bms.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.bms.model.Investment;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long>{

	List<Investment> findAllByPan(String pan);
	Investment findByFundId(long fundId);

}
