package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.model.LoginForm;
import com.example.demo.model.RegisterModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * ログインチェック
	 * なかった場合の処理がない
	 */
	public User userfind(LoginForm logmo) {
		List<User> userList = userRepository.findByUserIdAndUserPass(logmo.getUserId(), logmo.getPassword());
		User user = userList.get(0);
		return user;
	}

	/*
	 * ユーザー情報登録
	 */
	public boolean register(RegisterModel remo) {
		// TODO 自動生成されたメソッド・スタブ
		boolean chkInsert = false;
		User user = new User(remo.getId(),remo.getName(),remo.getPass(),remo.getTellNum(),remo.getEmail());

		user = userRepository.save(user);

		return chkInsert;
	}
}
