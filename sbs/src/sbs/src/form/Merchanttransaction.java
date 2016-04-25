package sbs.src.form;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchanttransaction")
public class Merchanttransaction {
	
	@Id
	@Column(name = "transactionid")
	@GeneratedValue
	private Integer transactionid;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "accountnumber")
	private Integer accountnumber;
	
	@Column(name = "customeraccountnumber")
	private Integer customeraccountnumber;
	
	
	@Column(name = "otp")
	private String otp;

	
	@Column(name = "amount")
	private String amount;

	@Column(name = "date")
	private String date;
	
	@Column(name = "transactionstatus")
	private String transactionstatus;


	
	
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	
	public Integer getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(Integer accountnumber) {
		this.accountnumber = accountnumber;
	}

	

	public Integer getCustomeraccountnumber() {
		return customeraccountnumber;
	}

	public void setCustomeraccountnumber(Integer customeraccountnumber) {
		this.customeraccountnumber = customeraccountnumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTransactionstatus() {
		return transactionstatus;
	}

	public void setTransactionstatus(String transactionstatus) {
		this.transactionstatus = transactionstatus;
	}

	public Merchanttransaction(){
		
	}

	@Override
	public String toString() {
		return "Merchanttransaction [transactionid=" + transactionid
				+ ", username=" + username + ", accountnumber=" + accountnumber
				+ ", customeraccountnumber=" + customeraccountnumber + ", otp="
				+ otp + ", amount=" + amount + ", date=" + date
				+ ", transactionstatus=" + transactionstatus + "]";
	}

	public Merchanttransaction(Integer transactionid, String username,
			Integer accountnumber, Integer customeraccountnumber, String otp,
			String amount, String date, String transactionstatus) {
		super();
		this.transactionid = transactionid;
		this.username = username;
		this.accountnumber = accountnumber;
		this.customeraccountnumber = customeraccountnumber;
		this.otp = otp;
		this.amount = amount;
		this.date = date;
		this.transactionstatus = transactionstatus;
	}

	
	


	
}