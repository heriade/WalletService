package com.interview.walletservice.repo;
import com.interview.walletservice.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account, Long> {
	@Query(value="SELECT * FROM accounts where email=?1", nativeQuery=true)
	Account findByEmail(String email);
}
