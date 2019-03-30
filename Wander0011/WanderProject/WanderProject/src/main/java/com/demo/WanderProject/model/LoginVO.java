/**
 * 
 */
package com.demo.WanderProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bharat Kumar
 *
 */

@Entity
@Table(name = "mdc_user")
public class LoginVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_srlno")
	private int id;
	@Column(name = "user_id")
	private String userName;

	@Column(name = "user_password")
	private String password;
	@Column(name = "role_id")
	private int roleId;
	@Column(name = "active_flg")
	private int activeFlag;
	
	private String roleDescription;
	
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	
	@Column(name = "email")
	private String email;
	
	@Column(name = "name")
	private String name;
	
	
	

	

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
