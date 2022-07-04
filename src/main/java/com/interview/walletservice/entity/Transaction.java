package com.interview.walletservice.entity;

import javax.persistence.*;
import java.sql.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50, unique = true)
	private String transId;
	
	@Column(nullable = false, precision=10, scale=2)
	private Double deposit;
	
	@Column(nullable = false, precision=10, scale=2)
	private Double withdrawl;
	
	@Column(nullable = false, length = 255)
	private String walletId;
	
	@CreationTimestamp
    private Date createdOn;
 
	@UpdateTimestamp
    private Date updatedOn;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getWithdrawl() {
		return withdrawl;
	}

	public void setWithdrawl(Double withdrawl) {
		this.withdrawl = withdrawl;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
