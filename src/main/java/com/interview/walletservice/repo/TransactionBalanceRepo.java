package com.interview.walletservice.repo;
import com.interview.walletservice.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

public interface TransactionBalanceRepo extends JpaRepository<TransactionBalance, Long> {
	
	@Query(value="SELECT balance FROM transaction_balances WHERE wallet_id=?1", nativeQuery=true)
	Double findByWalletId(String id);
	
	@Modifying
	@Query(value="UPDATE transaction_balances SET balance = balance + :bal WHERE wallet_id = :id", nativeQuery=true)
	void updateWalletBalanceFund(Double bal, String id);
	
	@Modifying
	@Query(value="UPDATE transaction_balances SET balance = balance - :bal WHERE wallet_id = :id", nativeQuery=true)
	void updateWalletBalanceCharge(Double bal, String id);
}
