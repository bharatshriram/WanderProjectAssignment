/**
 * 
 */
package com.demo.WanderProject.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.CommunityResponseVO;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.model.NoteVO;
import com.demo.WanderProject.model.TariffVO;
import com.demo.WanderProject.response.vo.NoteResponseVO;
import com.demo.WanderProject.response.vo.ResponseVO;

/**
 * @author Bharat Kumar
 *
 */

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommunitySetUpDAOImpl implements CommunitySetUpDAO {

	Gson gson=new Gson();
	
	@Autowired
	private SessionFactory sessionfactory;
	
	
	@Override
	public ResponseVO signupDataDAO(LoginVO loginvo) throws BusinessException {
		// TODO Auto-generated method stub
		ResponseVO responsevo =new ResponseVO();
		try {
			System.out.println("===>"+loginvo.getName());
			sessionfactory.getCurrentSession().saveOrUpdate(loginvo);
			
			responsevo.setResult("Sucess");
			
		}catch (Exception e) {
			// TODO: handle exception 
			throw new BusinessException(e);
		}
		return responsevo;
	}


	@Override
	public boolean checkifEmailExist(LoginVO loginvo) throws BusinessException {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		try {
		System.out.println(sessionfactory.getCurrentSession()+"!!!@@@@@@@@@@"+loginvo.getPassword());
		Query query = sessionfactory.getCurrentSession().createQuery("select mdc.email from LoginVO as mdc where mdc.email=:p1");
		query.setString("p1", loginvo.getEmail());
		System.out.println("Total");
		System.out.println("TotaSDl");
		List l =query.list();
		System.out.println("Total Number Of Records : "+l.size());
		Iterator it = l.iterator();

		if(it.hasNext())
		{
			flag=true;
			System.out.println("oooo==>"+loginvo.getRoleDescription());
			
			
		}else {
		flag=false;
		}
	} catch (Exception e) {
		// TODO: handle exception
		throw new BusinessException(e);
		
	} finally {
		
	}
		return flag;
	}


	@Override
	public ResponseVO noteDataDAO(NoteVO notevo) throws BusinessException {
		// TODO Auto-generated method stub
		ResponseVO responsevo =new ResponseVO();
		try {
			System.out.println("===>"+notevo.getTitle());
			
			if(notevo.getNoteId() == 0) {
				 	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				 	LocalDateTime now = LocalDateTime.now();
				   notevo.setUpdateTime(dtf.format(now));
				sessionfactory.getCurrentSession().saveOrUpdate(notevo);	
			}
			else {
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				   LocalDateTime now = LocalDateTime.now();
				   
				   notevo.setUpdateTime(dtf.format(now));
				   
				   
				   System.out.println(dtf.format(now));
				   
			//	sessionfactory.getCurrentSession().saveOrUpdate(notevo);
			}
			responsevo.setResult("Sucess");
		}catch (Exception e) {
			// TODO: handle exception 
			throw new BusinessException(e);
		}
		return responsevo;
	}


	@Override
	public List<NoteResponseVO> getNotedetails(int roleId) throws BusinessException {
		// TODO Auto-generated method stub
		
		List<NoteResponseVO> note_list = null;
		try {
			
			System.out.println(sessionfactory.getCurrentSession()+"!!!@@@@@@@@@@");
			
			note_list = new LinkedList<NoteResponseVO>();
			
			 Query query = sessionfactory.getCurrentSession().createQuery("from NoteResponseVO where roleId=:p1");
			 query.setInteger("p1", roleId);
			 note_list = query.list();
			 
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(e);
			
		} finally {
			//sessionfactory.getCurrentSession().close();
		}
		
		return note_list;
	}


	@Override
	@Transactional
	public String getNotedelete(int roleId) throws BusinessException {
		// TODO Auto-generated method stub

		String result="";
		try {
		//	NoteVO notevo =new NoteVO();
			//notevo.setNoteId(roleId);
			Query query = sessionfactory.getCurrentSession().createQuery("delete NoteVO where noteId = :p1");
			query.setParameter("p1", roleId);
			 
			int result1 = query.executeUpdate();
			 
			if (result1 > 0) {
			    System.out.println("Expensive Note was removed");
			}
			System.out.println(sessionfactory.getCurrentSession()+"!!!@@@@@@@@@@");
			
		//	sessionfactory.getCurrentSession().delete(notevo);
			
			
			/*NoteVO notevo = (NoteVO) sessionfactory.getCurrentSession().load(
					NoteVO.class, roleId);
	        if (null != notevo) {
	        	System.out.println("Result==>");
	        	this.sessionfactory.getCurrentSession().delete(notevo);
	        }*/
			 
			 result="success";
			 
			 System.out.println("Result==>"+result);
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(e);
			
		} finally {
			//sessionfactory.getCurrentSession().close();
		}
		return result;
	}


	@Override
	public boolean checkifUserExist(LoginVO loginvo) throws BusinessException {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
		Query query = sessionfactory.getCurrentSession().createQuery("select mdc.email from LoginVO as mdc where mdc.userName=:p1");
		query.setString("p1", loginvo.getUserName());
		System.out.println("Total");
		System.out.println("TotaSDl");
		List l =query.list();
		Iterator it = l.iterator();

		if(it.hasNext())
		{
			flag=true;
		}else {
		}
	} catch (Exception e) {
		// TODO: handle exception
		throw new BusinessException(e);
		
	} finally {
	}
		return flag;
	}

}
