package com.interview.walletservice.controller;
import org.springframework.web.bind.annotation.*;

import com.interview.walletservice.repo.*;
import com.interview.walletservice.entity.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController()
public class WalletAccountController {
	@Autowired
	private AccountRepo accountrepo;
	
	@PostMapping("/api/account/create")
	public Account createAccount(@RequestBody Account acct) {
		return accountrepo.save(acct);
	}
	
	@PostMapping("/api/account/login")
	public Account accountLogin(@RequestBody String email){
		return accountrepo.findByEmail(email);
	}
}
