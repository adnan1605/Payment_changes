package com.payment.paymententity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_details")
public class Payments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "user_name")
	private String userName;
	
	
	private Date date;
	private double initialAmount;
	private double amountTransacted;
	
	

	public Payments() {
		
	}

	public Payments( String userName, Date date, double initialAmount, double amountTransacted) {
		super();
		this.userName = userName;
		this.date = date;
		this.initialAmount = initialAmount;
		this.amountTransacted = amountTransacted;
	}

	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public double getInitialAmount() {
		return initialAmount;
	}



	public void setInitialAmount(double initialAmount) {
		this.initialAmount = initialAmount;
	}



	public double getAmountTransacted() {
		return amountTransacted;
	}



	public void setAmountTransacted(double amountTransacted) {
		this.amountTransacted = amountTransacted;
	}



	@Override
	public String toString() {
		return "Products [UserId=" + userId + ", UserName=" + userName + ", InitialAmount=" + initialAmount
				+ ", AmountTransacted=" + amountTransacted + "]";
	}
	
	

}
