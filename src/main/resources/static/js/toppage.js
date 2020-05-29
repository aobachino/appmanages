function search(){
	var formValues = document.querySelectorAll(".search").values

	$.ajax({
		url:"search",
		type:"POST",
		data:formValues,
		success:function(res){
			//検索データを取得
			console.log(res)
		},
		error:function(res){
			var errorobj = document.getElementById("error")
			var objP = document.createElement("p")
			var msg = document.createTextNode("不正なリクエストです")
			objP.setAttribute("id","error_msg")
			objP.setAttribute("class","alert-warning")
			objP.appendChild(msg)
			errorobj.appendChild(objP)
		}
	});
}