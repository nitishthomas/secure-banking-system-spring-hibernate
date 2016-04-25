package sbs.src.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchantaccount")
public class Merchantaccount {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "accountnumber")
	private Integer accountnumber;
	
	@Column(name = "balance")
	private int balance;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Merchantaccount(){
		
	}
	
	
	public Merchantaccount(Integer id, String username,
			Integer accountnumber, int balance) {
		this.id = id;
		this.username = username;
		this.accountnumber = accountnumber;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "externaluseraccount [id=" + id + ", username=" + username
				+ ", accountnumber=" + accountnumber + ", balance=" + balance
				+ "]";
	}



}
