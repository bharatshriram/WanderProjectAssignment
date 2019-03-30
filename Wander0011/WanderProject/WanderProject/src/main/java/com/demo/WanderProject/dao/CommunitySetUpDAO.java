/**
 * 
 */
package com.demo.WanderProject.dao;


import java.util.List;

import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.model.NoteVO;
import com.demo.WanderProject.response.vo.NoteResponseVO;
import com.demo.WanderProject.response.vo.ResponseVO;

/**
 * @author Bharat Kumar
 *
 */
public interface CommunitySetUpDAO {

	
	public ResponseVO signupDataDAO(LoginVO loginvo) throws BusinessException;

	public boolean checkifEmailExist(LoginVO loginvo) throws BusinessException;

	public ResponseVO noteDataDAO(NoteVO notevo) throws BusinessException;

	public List<NoteResponseVO> getNotedetails(int roleId) throws BusinessException;

	public String getNotedelete(int roleId) throws BusinessException;

	public boolean checkifUserExist(LoginVO loginvo) throws BusinessException;

}
