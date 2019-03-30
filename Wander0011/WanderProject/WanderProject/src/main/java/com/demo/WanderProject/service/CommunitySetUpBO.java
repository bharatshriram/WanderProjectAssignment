/**
 * 
 */
package com.demo.WanderProject.service;

import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.model.NoteVO;
import com.demo.WanderProject.response.vo.ResponseVO;

/**
 * @author Bharat Kumar
 *
 */
public interface CommunitySetUpBO {

	ResponseVO signupAdd(LoginVO loginvo) throws BusinessException;

	ResponseVO noteAdd(NoteVO notevo) throws BusinessException;
	
}
