/**
 * 
 */
package com.demo.WanderProject.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.WanderProject.constants.ExtraConstants;
import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.response.vo.ResponseVO;
import com.demo.WanderProject.utils.Encryptor;

/**
 * @author Bharat Kumar
 *
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	SessionFactory sessionfactory;
	
	/*@Autowired*/
	//private ResponseVO responsevo;
	
	@Override
	public ResponseVO LoginCheckDAO(LoginVO loginvo)throws ClassNotFoundException,BusinessException,SQLException {
		// TODO Auto-generated method stub
		ResponseVO responsevo =new ResponseVO();
		try {
			System.out.println(sessionfactory.getCurrentSession()+"!!!@@@@@@@@@@"+loginvo.getPassword());
			Query query = sessionfactory.getCurrentSession().createQuery("select mdc.userName,mdc.id,mdc.email,mdc.name from LoginVO as mdc where mdc.userName=:p1 and mdc.password=:p2");
			query.setString("p1", loginvo.getUserName());
			query.setString("p2", loginvo.getPassword());
			System.out.println("Total");
			System.out.println("TotaSDl");
			List l =query.list();
			System.out.println("Total Number Of Records : "+l.size());
			Iterator it = l.iterator();

			if(it.hasNext())
			{
				Object[] obj=(Object[]) it.next();
				
				System.out.println(obj[1].toString()+"===>"+obj[0].toString());
				
				responsevo.setRoleId(obj[1].toString());
				responsevo.setEmail(obj[2].toString());
				responsevo.setName(obj[3].toString());
				
				responsevo.setResult("Success");
				responsevo.setMessage("Successfully Logged In");
				
			}else {
				responsevo.setResult("Failure");
				responsevo.setMessage("INVALID CREDENTIALS");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(e);
			
		} finally {
			
		}
		return responsevo;
	}

	@Override
	public boolean checkuserid(String userid) throws SQLException {
		// TODO Auto-generated method stub

		boolean result = false;

		try {
			
			System.out.println("Session ===>"+sessionfactory.getCurrentSession());

			Query query = sessionfactory.getCurrentSession().createQuery("select mdc.userName from LoginVO as mdc where mdc.userName=:p1");
			query.setString("p1", userid);
			List l =query.list();
			System.out.println("Total Number Of Records : "+l.size());
			Iterator it = l.iterator();

			if(it.hasNext())
			{
				result = true;
			}else {
				result = false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		//	sessionfactory.getCurrentSession().close();
		}
		return result;
	}

	@Override
	public ResponseVO forgotpassword(String userid) {
		// TODO Auto-generated method stub
		String userPassword = null;
		String userEmail = null;

		ResponseVO responsevo = new ResponseVO();

		final String senderEmail = "alert@abc.com";// change
		// accordingly
		try {

			Query query = sessionfactory.getCurrentSession().createQuery("select mdc.userName,mdc.id,mdc.email,mdc.name,mdc.password from LoginVO as mdc where mdc.userName=:p1");
			query.setString("p1", userid);
			List l =query.list();
			System.out.println("Total Number Of Records : "+l.size());
			Iterator it = l.iterator();

			if(it.hasNext())
			{
				Object[] obj=(Object[]) it.next();
				
				System.out.println(obj[1].toString()+"===>"+obj[0].toString());
				
				userEmail=obj[2].toString();
				userPassword=obj[4].toString();
			}else {
				responsevo.setResult("Failure");
			}
			
		//	if(!responsevo.getResult().equalsIgnoreCase("Failure")) {
				userPassword = Encryptor.decrypt(ExtraConstants.key1, ExtraConstants.key2,
						userPassword);

				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");

				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(senderEmail, "123");// change
																						// accordingly
					}
				});

				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(senderEmail));// change
																		// accordingly
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
					message.setSubject("Password for " + userid);
					message.setText("Password for " + userid + " is : " + userPassword);

					Transport.send(message);
					responsevo.setResult("Success");
					responsevo.setMessage("Your Password Has Been Sent To your Registered Email");

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			//}
				

			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

		return responsevo;
	}

}
