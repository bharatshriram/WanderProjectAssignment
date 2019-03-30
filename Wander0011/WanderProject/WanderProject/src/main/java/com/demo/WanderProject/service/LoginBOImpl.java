/**
 * 
 */
package com.demo.WanderProject.service;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.WanderProject.constants.ExtraConstants;
import com.demo.WanderProject.dao.LoginDAO;
import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.response.vo.ResponseVO;
import com.demo.WanderProject.utils.Encryptor;

/**
 * @author Bharat Kumar
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginBOImpl implements LoginBO {
	
	@Autowired
	LoginDAO logindao;
	

	@Override	
	public ResponseVO CheckLogin(LoginVO loginvo)
			throws ClassNotFoundException, BusinessException,SQLException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();

	//	LoginDAO logindao = new LoginDAO();

		if (loginvo.getUserName().isEmpty() || loginvo.getPassword().isEmpty()) {

			throw new BusinessException("ENTER USERNAME AND PASSWORD");
		}
		
		loginvo.setPassword(Encryptor.encrypt(ExtraConstants.key1,
				ExtraConstants.key2, loginvo.getPassword()));

		responsevo = logindao.LoginCheckDAO(loginvo);

		return responsevo;
	}

	@Override
	public ResponseVO forgotpassword(String userid) throws MessagingException, BusinessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = new ResponseVO();

		if (userid.isEmpty()) {

			throw new BusinessException("Enter User ID");
		}
		
		System.out.println("ooCode===>"+userid);
		
		boolean checkuserid = logindao.checkuserid(userid);
		
		System.out.println("Code===>"+checkuserid);
		
		if(!checkuserid){
			throw new BusinessException("User has not yet Registered");
		}
		responsevo = logindao.forgotpassword(userid);
	

		return responsevo;
	}
}
