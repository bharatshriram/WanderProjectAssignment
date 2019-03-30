/**
 * 
 */
package com.demo.WanderProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.WanderProject.constants.ExtraConstants;
import com.demo.WanderProject.dao.CommunitySetUpDAO;
import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.model.NoteVO;
import com.demo.WanderProject.model.TariffVO;
import com.demo.WanderProject.response.vo.ResponseVO;
import com.demo.WanderProject.utils.Encryptor;

/**
 * @author Bharat Kumar
 *
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommunitySetUpBOImpl implements CommunitySetUpBO {

	@Autowired
	private CommunitySetUpDAO communitysetupdao;
	
	
	@Override
	@Transactional
	public ResponseVO signupAdd(LoginVO loginvo) throws BusinessException {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();

			if (loginvo.getEmail().isEmpty() || loginvo.getName().isEmpty() || loginvo.getPassword().isEmpty()) {

				throw new BusinessException("ALL FILEDS ARE REQURIED");
			}
			
			loginvo.setPassword(Encryptor.encrypt(ExtraConstants.key1,
					ExtraConstants.key2, loginvo.getPassword()));

			/*loginvo.setConfirmPassword(Encryptor.encrypt(ExtraConstants.key1,
					ExtraConstants.key2, loginvo.getConfirmPassword()));*/
			
			/*if(!loginvo.getPassword().equalsIgnoreCase(loginvo.getConfirmPassword())) {
				
				throw new BusinessException("Confirm Password is not Same");
				
			}*/
			
			boolean flag = communitysetupdao.checkifEmailExist(loginvo);
			System.out.println(flag);
			if (flag) { 
				throw new BusinessException(
						"Email ID Already Exist");
			}
			
			boolean flag1 = communitysetupdao.checkifUserExist(loginvo);

			if (flag1) { 
				throw new BusinessException(
						"User ID Already Exist");
			}
			
			responsevo = communitysetupdao.signupDataDAO(loginvo);

			return responsevo;
	}


	@Override
	@Transactional
	public ResponseVO noteAdd(NoteVO notevo) throws BusinessException {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();

		if (notevo.getTitle().isEmpty() || notevo.getDescription().isEmpty() || notevo.getCreation().isEmpty()) {

			throw new BusinessException("ALL FILEDS ARE REQURIED");
		}
		
		/*boolean flag = communitysetupdao.checkifEmailExist(loginvo);

		if (flag) { 
			throw new BusinessException(
					"Email ID Already Exist");
		}*/
		
		responsevo = communitysetupdao.noteDataDAO(notevo);

		return responsevo;
	}
}
