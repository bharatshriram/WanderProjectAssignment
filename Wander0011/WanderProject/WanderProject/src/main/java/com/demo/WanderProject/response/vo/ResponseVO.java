/**
 * 
 */
package com.demo.WanderProject.response.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Bharat Kumar
 *
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO {
	
	
	private String result;
	private String message;
	private String errorMessage;
	private String roleId;
	private String name;
	private String email;
	private List<NoteResponseVO> noteList; 
	
	
	
	
	
	
	public List<NoteResponseVO> getNoteList() {
		return noteList;
	}
	public void setNoteList(List<NoteResponseVO> noteList) {
		this.noteList = noteList;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
