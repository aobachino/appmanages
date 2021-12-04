package com.example.demo.common;

//共通のメッセージを管理
public interface ComMessage {

	 final String USER_NOTHING = "ID・パスワードが間違っています";

	 final String LOGIN_START = "ログイン処理開始";

	 final String LOGIN_END = "ログイン処理終了";

	 final String LOGIN_COMP = "ログイン完了";

	 final String USER_SEARCH_START = "ユーザー検索開始";

	 final String USER_SEARCH_END = "ユーザー検索終了";

	 final String USER_REGISTER_COMPLETE = "登録完了しました。ログインをして確認してください";

	 final String USER_REGISTER_FAILURE_SAME_INFO = "ユーザーIDはすでに使用されています";

	 final String USER_REGISTER_FAILURE_NOT_SAME_PASS = "パスワードと確認用パスワードは同じものを入力してください";

	 final String USER_REGISTER_FAILURE_NOT_SAME_EMAIL = "メールアドレスと確認用メールアドレスは同じものを入力してください";

	 final String USER_REGISTER_FAILURE = "登録失敗しました";

	 final String APPS_SEARCH_START = "求人検索開始";

	 final String APPS_SEARCH_END = "求人検索終了";

	 final String APPS_SEARCH_PAGEING_START = "ページング開始";

	 final String APPS_SEARCH_PAGEING_END = "ページング終了";

	 final String OFFER_FAILURE_OVER_CAPACITY  = "この求人の応募期限は過ぎております";

	 final String OFFER_COMPLETE  = "応募完了しました";

	 final String OFFER_FAILURE  = "応募に失敗しました。再度やり直してください";

}
