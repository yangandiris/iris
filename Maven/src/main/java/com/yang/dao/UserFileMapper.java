package com.yang.dao;

import com.yang.bean.UserFile;

public interface UserFileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated
     */
    int insert(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated
     */
    int insertSelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated
     */
    UserFile selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserFile record);
}