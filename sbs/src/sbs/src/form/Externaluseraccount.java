package sbs.src.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "externaluseraccount")
public class Externaluseraccount {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "accountnumber")
	private Integer accountnumber;
	
	@Column(name = "balance")
	private Integer balance;
	
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

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Externaluseraccount(){
		
	}
	
	
	public Externaluseraccount(Integer id, String username,
			Integer accountnumber, Integer balance) {
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
