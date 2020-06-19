package com.example.demo.model;

//ユーザー登録フォームモデル
public class RegisterModel {

	private String id;
	private String name;
	private String pass;
	private String passConf;
	private String tellNum;
	private String email;
	private String emailConf;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPassConf() {
		return passConf;
	}
	public void setPassConf(String passConf) {
		this.passConf = passConf;
	}
	public String getTellNum() {
		return tellNum;
	}
	public void setTellNum(String tellNum) {
		this.tellNum = tellNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailConf() {
		return emailConf;
	}
	public void setEmailConf(String emailConf) {
		this.emailConf = emailConf;
	}
}
