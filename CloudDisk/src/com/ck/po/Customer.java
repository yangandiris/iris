package com.ck.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Customer {
private long id;	
private String username;
private String password;
@Id
//@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")
//@SequenceGenerator(name="SEQ_GEN",sequenceName="Cus_sequence", allocationSize=1)
@GeneratedValue
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}
/*@Cascade(value={CascadeType.SAVE_UPDATE})  
@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")*/

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

}
