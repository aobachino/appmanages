package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.model.LoginForm;
import com.example.demo.model.RegisterModel;
import com.example.demo.repository.UserRepository;

//ユーザーDBとコントローラーの受け渡し
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * ログインチェック
	 * なかった場合の処理がない
	 */
	public boolean userfind(LoginForm logmo,HttpSession ses) {

		boolean chk = false;
		User user;

		synchronized(userRepository) {
			List<User> userList = userRepository.findByUserIdAndUserPass(logmo.getUserId(), logmo.getPassword());

			if(userList.size() == 1) {
				user = userList.get(0);
				chk = true;
				ses.setAttribute("user", user);
			}
		}

		return chk;
	}

	/*
	 * ユーザー情報登録
	 * エスケープ処理がない
	 */
	public int register(RegisterModel remo) {

		int chkInsert = 3;

		synchronized(userRepository){
			User user = new User(remo.getId(),remo.getName(),remo.getPass(),remo.getTellNum(),remo.getEmail());

			if(user != null) {
				user = userRepository.save(user);
				chkInsert = 0;
			}
		}

		return chkInsert;
	}

}
