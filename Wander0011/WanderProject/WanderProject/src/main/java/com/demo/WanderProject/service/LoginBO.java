/**
 * 
 */
package com.demo.WanderProject.service;

import java.sql.SQLException;

import javax.mail.MessagingException;

import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.response.vo.ResponseVO;

/**
 * @author Bharat Kumar
 *
 */


public interface LoginBO {
	
	public ResponseVO CheckLogin(LoginVO loginvo) throws ClassNotFoundException, BusinessException, SQLException;

	public ResponseVO forgotpassword(String userid) throws ClassNotFoundException, BusinessException, MessagingException, SQLException;
	
	

	
}
