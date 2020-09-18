package com.cts.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.bms.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByAccountNo(String account);
	
	Account findByPan(String pan);
	
	@Query(value="select count(acc.pan) from Account as acc where acc.pan=?1 and acc.account_no=?2", nativeQuery=true)
	int verifyAccount(String pan, String account);
	
	@Query(value="select count(acc.pan) from Account as acc where acc.pan=?1", nativeQuery=true)
	int countByPan(String pan);
	
	@Query(value="select count(acc.account_no) from Account as acc where acc.account_no=?1 and acc.pan=?2", nativeQuery=true)
	int countByPan(String account, String pan);


	List<Account> findAllByPan(String pan);
}

