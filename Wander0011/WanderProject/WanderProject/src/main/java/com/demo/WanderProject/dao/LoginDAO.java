/**
 * 
 */
package com.demo.WanderProject.dao;

import java.sql.SQLException;

import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.response.vo.ResponseVO;

/**
 * @author Bharat Kumar
 *
 */
public interface LoginDAO {

	public ResponseVO LoginCheckDAO(LoginVO loginvo) throws ClassNotFoundException, SQLException, BusinessException;
	
	public boolean checkuserid(String userid) throws ClassNotFoundException, SQLException, BusinessException;

	public ResponseVO forgotpassword(String userid) throws ClassNotFoundException, SQLException, BusinessException;
	
}
