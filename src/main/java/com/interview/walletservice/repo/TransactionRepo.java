package com.interview.walletservice.repo;

import com.interview.walletservice.entity.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	@Query(value="SELECT * FROM transactions WHERE wallet_id=?1", nativeQuery=true)
	List<Transaction> getTransHistory(String id);
}
