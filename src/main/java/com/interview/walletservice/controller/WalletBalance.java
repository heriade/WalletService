package com.interview.walletservice.controller;

public class WalletBalance {
	
	public Double balance;
	
	public WalletBalance(Double _balance) {
		this.balance=_balance;
	}
	
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
