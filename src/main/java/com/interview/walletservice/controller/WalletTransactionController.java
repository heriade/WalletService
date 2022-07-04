package com.interview.walletservice.controller;

import org.springframework.web.bind.annotation.*;
import com.interview.walletservice.repo.*;
import com.interview.walletservice.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
public class WalletTransactionController {
	@Autowired
	private TransactionRepo transrepo;
	
	@Autowired
	private TransactionBalanceRepo tbr;
	
	@GetMapping("/api/home")
	public String IndexPage() {
		return "hello world";
	}
	
	@GetMapping("/api/{id}/history")
	public List<Transaction> getWalletHistory(@PathVariable String id){
		return transrepo.getTransHistory(id);
	}
	
	@GetMapping("/api/{id}/balance")
	public WalletBalance getWalletBalance(@PathVariable String id) {
		return new WalletBalance(tbr.findByWalletId(id));
	}
	
	@PostMapping("/api/trans/fund")
	@Transactional
	public Transaction transFund(@RequestBody Transaction trans) {
		//check transaction before posting fund
		this.checkAmount(trans.getDeposit());
		
		//update wallet fund
		this.updateWalletFund(trans.getDeposit(), trans.getWalletId());
		
		//start posting fund
		trans.setDeposit(trans.getDeposit());
		trans.setWithdrawl(0.00);
		Double transid = Math.random();
		trans.setTransId("test"+transid);
		trans.setWalletId(trans.getWalletId());
		return transrepo.save(trans);
	}
	
	public void updateWalletFund(Double bal, String id) {
		Double chk = tbr.findByWalletId(id);
		if( chk == null){
			TransactionBalance tb = new TransactionBalance();
			tb.setBalance(bal);
			tb.setWalletId(id);
			tbr.save(tb);
		}else {
			tbr.updateWalletBalanceFund(bal, id);
		}
	}
	
	@PostMapping("/api/trans/charge")
	@Transactional
	public Transaction transCharge(@RequestBody Transaction trans) {
		//check transaction before posting charges
		this.checkAmount(trans.getWithdrawl());
		
		//check transaction before charging
		this.checkBalance(tbr.findByWalletId(trans.getWalletId()), trans.getWithdrawl());
		
		//update wallet charge
		this.updateWalletCharge(trans.getWithdrawl(), trans.getWalletId());
		
		//start posting charges
		trans.setDeposit(0.00);
		trans.setWithdrawl(trans.getWithdrawl());
		Double transid = Math.random();
		trans.setTransId("test"+transid);
		trans.setWalletId(trans.getWalletId());
		return transrepo.save(trans);
	}
	
	public void updateWalletCharge(Double bal, String id) {
		Double chk = tbr.findByWalletId(id);
		if( chk == null){
			TransactionBalance tb = new TransactionBalance();
			tb.setBalance(bal);
			tb.setWalletId(id);
			tbr.save(tb);
		}else {
			tbr.updateWalletBalanceCharge(bal, id);
		}
		
	}
	
	public void checkBalance(Double getbal,Double getWithdrawl) {
		if(getWithdrawl > getbal) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid transaction! Your balance is lower than what you charging");
		}
	}
	
	public void checkAmount(Double amount) {
		if(amount==0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid transaction! 0 amount cannot be fund or charge");
		}
	}
}
