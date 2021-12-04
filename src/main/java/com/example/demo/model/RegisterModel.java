package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//ユーザー登録フォームモデル
public class RegisterModel {

	@NotEmpty(message = "必須入力です ")
	@Size(max = 20, message = "20文字以内で入力してください")
	private String id;

	@NotEmpty(message = "必須入力です")
	@Size(max = 32, message = "32文字以内で入力してください")
	private String name;

	@NotEmpty(message = "必須入力です")
	@Pattern(regexp = "[a-zA-z0-9]*", message = "アルファベットと数字のみで入力してください")
	private String pass;

	@NotEmpty(message = "必須入力です")
	@Pattern(regexp = "[a-zA-z0-9]*", message = "アルファベットと数字のみで入力してください")
	private String passConf;

	@NotEmpty(message = "必須入力です")
	@Pattern(regexp = "[0-9]*", message = "正しいフォーマットで入力してください")
	private String tellNum;

	@NotEmpty(message = "必須入力です")
	private String email;

	@NotEmpty(message = "必須入力です")
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
