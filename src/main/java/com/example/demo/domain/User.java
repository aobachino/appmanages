package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//ユーザー情報DB
@Data
@Entity
@Table(name = "user")
public class User {

	public User(){
		   super();
	}

	public User(String userId,String userName,String userPass,String userTel,String userEmail) {
		setUserId(userId);
		setUserName(userName);
		setUserPass(userPass);
		setUserTel(userTel);
		setUserEmail(userEmail);
	}

	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_pass")
	private String userPass;

	@Column(name="user_tel")
	private String userTel;

	@Column(name="user_email")
	private String userEmail;
}
