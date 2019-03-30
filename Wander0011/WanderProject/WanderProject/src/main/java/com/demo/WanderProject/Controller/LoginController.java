/**
 * 
 */
package com.demo.WanderProject.Controller;

import java.net.StandardSocketOptions;
import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.demo.WanderProject.constants.ExtraConstants;
import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.response.vo.ResponseVO;
import com.demo.WanderProject.service.LoginBO;
import com.demo.WanderProject.utils.Encryptor;

/**
 * @author Bharat Kumar
 *
 */
@RestController
public class LoginController {
	
	@Autowired
	private LoginBO loginbo;
	
	Gson gson = new Gson();

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public 
	ResponseVO validateUser(@RequestBody LoginVO loginvo)
			throws ClassNotFoundException, BusinessException, SQLException {

		ResponseVO responsevo = new ResponseVO();
		
		System.out.println("===>"+loginvo.getPassword());

		/*if (loginvo.getUserName().isEmpty() || loginvo.getPassword().isEmpty()) {

			throw new BusinessException("ENTER USERNAME AND PASSWORD");
		}*/
		
		
		try {
			responsevo = loginbo.CheckLogin(loginvo);
		}catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setErrorMessage(message);
			responsevo.setResult("Failure");
		}
		return responsevo;
	}
	
	@RequestMapping(value = "/forgotpassword/{userid}", method = RequestMethod.GET, produces = "application/json")
	public
	ResponseVO forgotpassword(@PathVariable("userid") String userid)
			throws ClassNotFoundException, BusinessException, SQLException {

			ResponseVO responsevo = new ResponseVO();

		try {
			
			responsevo = loginbo.forgotpassword(userid);
			
			System.out.println("--If------------->"+responsevo.getMessage());
			
			responsevo.setResult("Success");
			
			String json = gson.toJson(responsevo);
			
			System.out.println("========>"+json);
			
		}
		 catch (BusinessException e) {
			 
			    System.out.println("--Message------------->"+e.getMessage());
			 
				String message = e.getMessage();
				
				responsevo.setErrorMessage(message);
				
				String json1 = gson.toJson(responsevo);
				
				System.out.println("Exception========>"+json1);
				
			}
		
		catch (MessagingException e) {
			
			String message = e.getMessage();
			
			responsevo.setErrorMessage(message);
			
		}
		
		String json2 = gson.toJson(responsevo);
		
		System.out.println("========>"+json2);
		
		return responsevo;
	}
	
	
	


}
