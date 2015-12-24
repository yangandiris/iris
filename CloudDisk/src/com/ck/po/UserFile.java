package com.ck.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class UserFile {
	public String getF_date() {
		return f_date;
	}
	public void setF_date(String f_date) {
		this.f_date = f_date;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_parent() {
		return f_parent;
	}
	public void setF_parent(String f_parent) {
		this.f_parent = f_parent;
	}
	public String getF_type() {
		return f_type;
	}
	public void setF_type(String f_type) {
		this.f_type = f_type;
	}
	public String getF_size() {
		return f_size;
	}
	public void setF_size(String f_size) {
		this.f_size = f_size;
	}
	public String getF_url() {
		return f_url;
	}
	public void setF_url(String f_url) {
		this.f_url = f_url;
	}
	public int getIsfolder() {
		return isfolder;
	}
	public void setIsfolder(int isfolder) {
		this.isfolder = isfolder;
	}
	
	private long id;
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")
	//@SequenceGenerator(name="SEQ_GEN",sequenceName="Userfile_sequence", allocationSize=1)
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getC_id() {
		return c_id;
	}
	public void setC_id(long c_id) {
		this.c_id = c_id;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public int getIslock() {
		return islock;
	}
	public void setIslock(int islock) {
		this.islock = islock;
	}
	
	private String f_name;
	private String f_parent;
	private String f_type;
	private String f_size;
	private String f_date;
	private String f_url;
	private long c_id;
	private int isfolder;
	private String md5;
	private int islock;
	
	
	
	
}
