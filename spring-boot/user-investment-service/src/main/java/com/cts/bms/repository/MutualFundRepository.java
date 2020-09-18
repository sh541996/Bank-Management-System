package com.cts.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.bms.model.MutualFund;

@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund, Long>{

}
