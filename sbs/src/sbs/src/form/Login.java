package sbs.src.form;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "users")
public class Login {

	
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Id
	@NotBlank(message="Username cannot be blank.")
	@Size(min=8,max=15,message="Username must be between 8 and 15 characters.")
	@Pattern(regexp="^\\w{8,}$",message="Username can consist of only numbers, letters and underscore character.")
	@Column(name = "username")
	private String username;

	@NotBlank(message="Password can not be blank.")
	@Pattern(regexp="^\\S+$")
	@Size(min=8,max=15,message= "Password must be 8 and 15 characters.")
	@Column(name = "password")
	private String password;
	
	@NotBlank(message="Firstname cannot be blank.")
	@Size(min=5,max=15,message="Firstname must be between 5 and 15 characters.")
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Email(message="This does not appear to be a valid email address.")
	@Column(name = "email")
	private String email;
	
	@Column(name = "attempts")
	private Integer attempts;
	
	@Column(name = "ssn")
	private Integer ssn;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "lastloginfailed")
	private Timestamp lastloginfailed;
	
	@Column(name = "phone")
	private Integer phone;
	
	@Column(name="privatekey")
	private Blob privatekey;
	
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Blob getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(Blob privatekey) {
		this.privatekey = privatekey;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public Integer getSsn() {
		return ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLastloginfailed() {
		return lastloginfailed;
	}

	public void setLastloginfailed(Timestamp lastloginfailed) {
		this.lastloginfailed = lastloginfailed;
	}

	
	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Login() {

	}

	public Login(Integer id, String username, String password,
			String firstname, String lastname, String email, Integer attempts,
			Integer ssn, String dob, String gender, Timestamp lastloginfailed, Integer phone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.attempts = attempts;
		this.ssn = ssn;
		this.dob = dob;
		this.gender = gender;
		this.lastloginfailed = lastloginfailed;
		this.phone = phone;
		
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", username=" + username + ", password="
				+ password + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", attempts=" + attempts
				+ ", ssn=" + ssn + ", dob=" + dob + ", gender=" + gender
				+ ", lastloginfailed=" + lastloginfailed + ", phone=" + phone
				+ "]";
	}

	

	
}
