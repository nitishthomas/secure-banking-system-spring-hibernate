package sbs.src.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import sbs.src.dao.FormValidationGroup;
import sbs.src.dao.PersistanceValidationGroup;
import sbs.src.validations.ValidEmail;

@Entity
@Table(name = "registeruser")
public class Registeruser {
	
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Id
	@NotBlank(groups={PersistanceValidationGroup.class , FormValidationGroup.class}, message="Username cannot be blank.")
	@Size(min=5,max=15,message="Username must be between 5 and 15 characters.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Pattern(regexp="^\\w{5,}$",message="Username can consist of only numbers, letters and underscore character.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Column(name = "username")
	private String username;

	@NotBlank(message="Firstname cannot be blank.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Pattern(regexp="^\\w{5,}$",message="Firstname can consist of only numbers, letters and underscore character.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Size(min=5,max=15,message= "Firstname must be 5 and 15 characters.",groups={FormValidationGroup.class})
	@Column(name = "firstname")
	private String firstname;
	
	@NotBlank(message="Lastname cannot be blank.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Pattern(regexp="^\\w{5,}$",message="Lastname can consist of only numbers, letters and underscore character.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Size(min=5,max=15,message= "Lasttname must be 5 and 15 characters.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Column(name = "lastname")
	private String lastname;
	
	@NotBlank(message="Password can not be blank.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Pattern(regexp="^\\S+$",message="Password should not contain spaces and be alphanumeric..",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Size(min=8,max=15,message= "Password must be 8 and 15 characters.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Please enter a valid email format..", groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	private String email;
	
//	@NotBlank(message="SSN cannot be blank.")
	//@Size(min=10,max=10,message="SSN should be a 10 digit number.")
	@Column(name = "ssn")
	private int ssn;
	
	@Column(name = "dob")
	private String dob;
	

	@NotBlank(message="Gender cannot be blank.",groups={PersistanceValidationGroup.class , FormValidationGroup.class})
	@Column(name = "gender")
	private String gender;
	
	
	@Column(name = "phone")
	private int phone;
	
	@Column(name = "activestatus")
	private String activestatus;
		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	
	public String getActivestatus() {
		return activestatus;
	}

	public void setActivestatus(String activestatus) {
		this.activestatus = activestatus;
	}

	public Registeruser(){
		
	}

	public Registeruser(int id, String username, String firstname,
			String lastname, String password, String email, int ssn,
			String dob, String gender, int phone, String activestatus) {
		
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.ssn = ssn;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.activestatus = activestatus;
	}

	@Override
	public String toString() {
		return "RegisterUser [id=" + id + ", username=" + username
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", email=" + email + ", ssn="
				+ ssn + ", dob=" + dob + ", gender=" + gender + ", phone="
				+ phone + ", activestatus=" + activestatus + "]";
	}
	
		

}
