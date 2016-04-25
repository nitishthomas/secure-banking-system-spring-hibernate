package sbs.src.form;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "externalusertransaction")
public class Externalusertransaction {
	
	@Id
	@Column(name = "transactionid")
	@GeneratedValue
	private Integer transactionid;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "accountnumber")
	private Integer accountnumber;
	
	@Column(name = "senderaccountnumber")
	private Integer senderaccountnumber;
	
	@Column(name = "receiveraccountnumber")
	private Integer receiveraccountnumber;
	
	@Column(name = "amount")
	private Integer amount;

	@Column(name = "date")
	private String date;
	
	@Column(name = "transactionstatus")
	private String transactionstatus;

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

	public Integer getSenderaccountnumber() {
		return senderaccountnumber;
	}

	public void setSenderaccountnumber(Integer senderaccountnumber) {
		this.senderaccountnumber = senderaccountnumber;
	}

	public Integer getReceiveraccountnumber() {
		return receiveraccountnumber;
	}

	public void setReceiveraccountnumber(Integer receiveraccountnumber) {
		this.receiveraccountnumber = receiveraccountnumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
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

	public Externalusertransaction(){
		
	}
	
	public Externalusertransaction(Integer transactionid, String username,
			Integer accountnumber, Integer senderaccountnumber,
			Integer receiveraccountnumber, Integer amount, String date,
			String transactionstatus) {
		this.transactionid = transactionid;
		this.username = username;
		this.accountnumber = accountnumber;
		this.senderaccountnumber = senderaccountnumber;
		this.receiveraccountnumber = receiveraccountnumber;
		this.amount = amount;
		this.date = date;
		this.transactionstatus = transactionstatus;
	}

	@Override
	public String toString() {
		return "externalusertransaction [transactionid=" + transactionid
				+ ", username=" + username + ", accountnumber=" + accountnumber
				+ ", senderaccountnumber=" + senderaccountnumber
				+ ", receiveraccountnumber=" + receiveraccountnumber
				+ ", amount=" + amount + ", date=" + date
				+ ", transactionstatus=" + transactionstatus + "]";
	}

	
}