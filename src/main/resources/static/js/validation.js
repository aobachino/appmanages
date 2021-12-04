//文字列チェック
$(function(){

	//ユーザーID
	$("#userId").on("input",function(){
		var userId = document.getElementById("userId").value
		var errobj = document.getElementById("userIdErr")
		var errormsg1 = document.createTextNode("※30文字以内で入力してください")
		var errormsg2 = document.createTextNode("※半角英数字で入力してください")
		var errId1 = "userIdErr1"
		var errId2 = "userIdErr2"

		if(userId.length > 30){

			makeMsg(errobj,errormsg1,errId1)
		}else{
			chkOk(errobj,errormsg1,errId1)
		}

		if(userId.match(/^[0-9a-zA-Z]*$/)){

			chkOk(errobj,errormsg2,errId2)
		}else{
			makeMsg(errobj,errormsg2,errId2)

		}
	});

	//名前チェック
	$("#userName").on("input",function(){

		var userName = document.getElementById("userName").value
		var errobj = document.getElementById("userNameErr")
		var errId1 = "userNameErr1"

		if(userName.length >= 50){
			var errormsg = document.createTextNode("50文字以内で入力してください")
			makeMsg(errobj,errormsg,errId1)
		}else{
			chkOk(errobj,errormsg,errId1)
		}

	});

	//パスワードチェック
	$("#userPass").on("input",function(){
		var userPass = document.getElementById("userPass").value
		var errobj = document.getElementById("userPassErr")
		var errormsg1 = document.createTextNode("10文字以上で入力してください")
		var errormsg2 = document.createTextNode("パスワードは半角英数字で入力してください")
		var errId1 = "userPassErr1"
		var errId2 = "userPassErr2"

		if(userPass.length < 10){

			makeMsg(errobj,errormsg1,errId1)
		}else{
			chkOk(errobj,errormsg1,errId1)
		}

		if(userPass.match(/^(?=.*?[a-z])(?=.*?\d)[a-z\d]$/)){//パターンを治す
			chkOk(errobj,errormsg2,errId2)
		}else{
			makeMsg(errobj,errormsg2,errId2)

		}
	});

	//パスワード確認チェック
	$("#userPassConf").on("input",function(){

		var userPass = document.getElementById("userPass").value
		var userPassConf = document.getElementById("userPassConf").value
		var errobj = document.getElementById("userPassConfErr")
		var errormsg = document.createTextNode("同じパスワードを入力してください")
		var errId1 = "userPassConfErr1"

		if(userPass != userPassConf){
			makeMsg(errobj,errormsg,errId1)
		}else{
			chkOk(errobj,errormsg,errId1)
		}
	});

	//電話番号
	$("#userTell").on("input",function(){
		var userTell = document.getElementById("userTell").value
		var errobj = document.getElementById("userTellErr")
		var errormsg1 = document.createTextNode("12文字以内で入力してください")
		var errormsg2 = document.createTextNode("電話番号の入力きそくに従ってください")
		var errId1 = "userTellErr1"
		var errId2 = "userTellErr2"

		if(userTell.length >= 12){

			makeMsg(errobj,errormsg1,errId1)
		}else{
			chkOk(errobj,errormsg1,errId1)
		}

		if(!(userTell.match(/^[0-9]*$/))){

			makeMsg(errobj,errormsg2,errId2)
		}else{
			chkOk(errobj,errormsg2,errId2)
		}
	});

	//メールアドレス
	$("#userEmail").on("input",function(){

		var userEmail = document.getElementById("userEmail").value
		var errobj = document.getElementById("userEmailErr")
		var errormsg = document.createTextNode("メールアドレスの入力規則に従ってください")
		var errId1 = "userEmailErr1"

		if(userEmail.match(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/)){
			chkOk(errobj,errormsg,errId1)

		}else{
			makeMsg(errobj,errormsg,errId1)
		}
	});

	//メールアドレス確認
	$("#userEmailConf").on("input",function(){

		var userEmail = document.getElementById("userEmail").value
		var userEmailConf = document.getElementById("userEmailConf").value
		var errobj = document.getElementById("userEmailConfErr")
		var errormsg = document.createTextNode("同じメールアドレスを入力してください")
		var errId1 = "userEmailConfErr1"

		if(userEmail != userEmailConf){

			makeMsg(errobj,errormsg,errId1)
		}else{
			chkOk(errobj,errormsg,errId1)
		}
	});

	//チェック成功　サーバー側に寄せる
	function chkOk(errobj,errmsg,errId) {
		//p要素を削除する
		var pNode = document.getElementById(errId)

		if( errobj.innerText.includes(errmsg.nodeValue) ){
			pNode.remove()
		}
	}

	//チェック失敗サーバー側に寄せる
	function makeMsg(errobj,errmsg,errId){
		if(!( errobj.innerText.includes(errmsg.nodeValue) )){
			//メッセージ作成
			var objP  = document.createElement("p")
			objP.setAttribute("class",'alert alert-danger')
			objP.setAttribute("id",errId)
			objP.appendChild(errmsg)
			errobj.appendChild(objP)
		}

	}
});