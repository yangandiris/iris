package com.yang.bean;

public class UserEntity {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DB2ADMIN.TEST.ID
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column DB2ADMIN.TEST.BYZI
	 * @mbggenerated
	 */
	private String byzi;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DB2ADMIN.TEST.ID
	 * @return  the value of DB2ADMIN.TEST.ID
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DB2ADMIN.TEST.ID
	 * @param id  the value for DB2ADMIN.TEST.ID
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column DB2ADMIN.TEST.BYZI
	 * @return  the value of DB2ADMIN.TEST.BYZI
	 * @mbggenerated
	 */
	public String getByzi() {
		return byzi;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column DB2ADMIN.TEST.BYZI
	 * @param byzi  the value for DB2ADMIN.TEST.BYZI
	 * @mbggenerated
	 */
	public void setByzi(String byzi) {
		this.byzi = byzi == null ? null : byzi.trim();
	}
}