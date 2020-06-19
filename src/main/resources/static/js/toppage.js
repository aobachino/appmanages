$(function(){
	$("#top").ready(function(event){
		$('#btn-search').on("click",function(event){

			var forms = $(".search").serialize()
			console.log(forms)
			$.ajax({
				type:"GET",
				url:"search",
				dataType:"json",
				data:forms,
				success:function(data,dataType){

					//通信成功
					$(".tr-data").remove()
					if(data.length != 0){
						console.log(data)
						var table = document.getElementById("apps")
						for(var i=0;i<data.length;i++){

							var tr = table.insertRow()

							var td1 = tr.insertCell()
							var td2 = tr.insertCell()
							var td3 = tr.insertCell()
							var td4 = tr.insertCell()

							var text1 = document.createTextNode(data[i].appName)
							var text2 = document.createTextNode(data[i].companyName)
							var text3 = document.createTextNode(data[i].occupationName)

							tr.setAttribute("class","tr-data")
							var button = document.createElement("button")
							var btntext = document.createTextNode("応募")
							button.setAttribute("class","btn btn-primary")
							button.setAttribute("id",data[i].appId)
							button.setAttribute("name","appid")
							button.value = data[i].appId
							button.appendChild(btntext)

							td1.appendChild(text1)
							td2.appendChild(text2)
							td3.appendChild(text3)
							td4.appendChild(button)
						}
					}else{
						var msgobj = document.getElementById("search_zero")
						if(!(msgobj.hasChildNodes())){
							var objP = document.createElement("p")
							var msg = document.createTextNode("検索結果は0件です")
//							objP.setAttribute("id","search_zero")
//							objP.setAttribute("class","alert-warning")
							objP.appendChild(msg)
							msgobj.appendChild(objP)
						}
					}
				},
				error:function(){
					// 通信失敗
					var errorobj = document.getElementById("error")
					if(!(errorobj.hasChildNodes())){
						var objP = document.createElement("p")
						var msg = document.createTextNode("不正なリクエストです")
						objP.setAttribute("id","error_msg")
						objP.setAttribute("class","alert-warning")
						objP.appendChild(msg)
						errorobj.appendChild(objP)
					}
				}
			})
			return false;
		})
		return false;
	})

	$("#top").ready(function(event){
		$.ajax({
			type:"GET",
			url:"topdata",
			dataType:"json",
			//通信成功
			success:function(data,dataType){
				//通信成功
				$(".tr-data").remove()
				var table = document.getElementById("apps")
				for(var i=0;i<data.length;i++){

					var tr = table.insertRow()

					var td1 = tr.insertCell()
					var td2 = tr.insertCell()
					var td3 = tr.insertCell()
					var td4 = tr.insertCell()

					var text1 = document.createTextNode(data[i].appName)
					var text2 = document.createTextNode(data[i].companyName)
					var text3 = document.createTextNode(data[i].occupationName)

					tr.setAttribute("class","tr-data")
					var button = document.createElement("button")
					var btntext = document.createTextNode("応募")
					button.setAttribute("class","btn btn-primary")
					button.setAttribute("id",data[i].appId)
					button.setAttribute("name","appid")
					button.value = data[i].appId
					button.appendChild(btntext)

					td1.appendChild(text1)
					td2.appendChild(text2)
					td3.appendChild(text3)
					td4.appendChild(button)
				}
			},
			error:function(){
				// 通信失敗
				var errorobj = document.getElementById("error")
				if(!(errorobj.hasChildNodes())){
					var objP = document.createElement("p")
					var msg = document.createTextNode("不正なリクエストです")
					objP.setAttribute("id","error_msg")
					objP.setAttribute("class","alert-warning")
					objP.appendChild(msg)
					errorobj.appendChild(objP)
				}
			}
		})
		return false;
	})
	return false;
})
