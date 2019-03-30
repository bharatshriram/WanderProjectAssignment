/**
 * 
 */
package com.demo.WanderProject.Controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.demo.WanderProject.dao.CommunitySetUpDAO;
import com.demo.WanderProject.expections.BusinessException;
import com.demo.WanderProject.model.LoginVO;
import com.demo.WanderProject.model.NoteVO;
import com.demo.WanderProject.model.TariffVO;
import com.demo.WanderProject.response.vo.ResponseVO;
import com.demo.WanderProject.service.CommunitySetUpBO;

/**
 * @author Bharat Kumar
 *
 */

@RestController

public class CustomerSetUp {

	@Autowired
	private CommunitySetUpDAO communitysetupdao;

	@Autowired
	private CommunitySetUpBO communitysetupbo;

	Gson gson = new Gson();
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() throws IOException {
		System.out.println("===>");

		  return "index";
	}*/

	@CrossOrigin
	@RequestMapping(value = "/note/list/{roleId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseVO Notedetails(@PathVariable("roleId") int roleId) throws SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();

		responsevo.setNoteList(communitysetupdao.getNotedetails(roleId));

		System.out.println("--------->" + responsevo.getNoteList());

		String data2 = gson.toJson(responsevo);

		return responsevo;
	}

	@CrossOrigin
	@RequestMapping(value = "/data/signup/add", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public ResponseVO signUpAdd(@RequestBody LoginVO loginvo) throws SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		try {
			
			System.out.println("inside");
			responsevo = communitysetupbo.signupAdd(loginvo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setErrorMessage(message);
			responsevo.setResult("Failure");

		}
		return responsevo;
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/data/note/add/edit/{noteId}", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	public ResponseVO NoteAdd(@RequestBody NoteVO notevo, @PathVariable("noteId") int noteId) throws SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		try {
			
			notevo.setNoteId(noteId);
			responsevo = communitysetupbo.noteAdd(notevo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setErrorMessage(message);
			responsevo.setResult("Failure");
		}
		return responsevo;
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/note/delete/{roleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseVO Notedelete(@PathVariable("roleId") int roleId) throws SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		
		System.out.println("roleId==>"+roleId);

		responsevo.setResult(communitysetupdao.getNotedelete(roleId));

		//System.out.println("--------->" + responsevo.getNoteList());

		String data2 = gson.toJson(responsevo);

		return responsevo;
	}
	
	
}
