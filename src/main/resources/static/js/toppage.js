$(function(){

	$("#top").ready(function(event){

		//初回起動時、ページ読み込み時
		$.ajax({
			type:"GET",
			url:"topdata",
			dataType:"json",
			//通信成功
			success:function(data,dataType){

				success(data)
				pageButtonChanged(1)

			},
			// 通信失敗
			error:function(){

				error()
			}
		})

		// 検索ボタン押下時にキャッチ
		$('#btn-search').on("click",function(event){

			var forms = $(".search").serialize()
			console.log(forms)
			$.ajax({
				type:"GET",
				url:"search",
				dataType:"json",
				data:forms,
				// 通信成功
				success:function(data,dataType){

					success(data)
					pageButtonChanged(1)
				},
				// 通信失敗
				error:function(){

					error()
				}
			})
			return false;//$('#btn-search')
		})

		// ページ変更ボタン押下時にキャッチ
		$(document).on('click', 'button.page-link',function(event){

			var forms = $(event.target).val()
			var obj = {
				pageNum: forms
			}

			//ページ変更時に検索結果を受け取る
			$.ajax({
				type:"GET",
				url:"paging",
				dataType:"json",
				data:obj,
				// 通信成功
				success:function(data,dataType){

					success(data)
					pageButtonChanged(forms)
				},
				// 通信失敗
				error:function(){

					error()
				}
			})
			return false;//$('.page-link')
		})
		return false;//$("#top")
	})

	//通信成功時の処理
	function success(data){

		if($("#error").length){
			$("#error").children('p').remove();
		}

		if($("#search_zero").length){
			$("#search_zero").children('p').remove();
		}

		$(".apps-data").remove()
		if(data.length != 0){
			console.log(data)
			var div = document.getElementById("apps")
			var br = document.createElement("br")
			for(var i=0;i<data.length;i++){

				var div1 = document.createElement("div")
				div1.setAttribute("class","card apps-data")

				var div2 = document.createElement("div")
				div2.setAttribute("class","card-body")

				var svg = document.createElement("svg")
				var img = document.createElement("img")
				img.src = ""
					svg.setAttribute("class","bd-placeholder-img card-img-top")
					svg.setAttribute("width","600")
					svg.setAttribute("height","100")
					svg.setAttribute("viewBox","0 0 600 100")
					svg.appendChild(img)

					var h5 = document.createElement("h5")
					var h5text = document.createTextNode(data[i].appName)
					h5.setAttribute("class","card-title")
					h5.appendChild(h5text)

					var h6_1 = document.createElement("h6")
					var h6_1_text = document.createTextNode(data[i].companyName)
					h6_1.setAttribute("class","card-subtitle")
					h6_1.appendChild(h6_1_text)

					var h6_2 = document.createElement("h6")
					var h6_2_text = document.createTextNode(data[i].occupationName)
					h6_2.setAttribute("class","card-subtitle")
					h6_2.appendChild(h6_2_text)

					var p = document.createElement("p")
					p.setAttribute("class","card-text")

					var button = document.createElement("button")
					var btntext = document.createTextNode("応募")
					button.setAttribute("class","btn btn-primary card-link")
					button.setAttribute("id",data[i].appId)
					button.setAttribute("name","appid")
					button.value = data[i].appId
					button.appendChild(btntext)

					div2.appendChild(svg)
					div2.appendChild(h5)
					div2.appendChild(h6_1)
					div2.appendChild(h6_2)
					div2.appendChild(p)
					div2.appendChild(button)
					div1.appendChild(div2)
					div.appendChild(div1)
			}
		}else{
			var msgobj = document.getElementById("search_zero")
			if(!(msgobj.hasChildNodes())){
				var objP = document.createElement("p")
				var msg = document.createTextNode("検索結果は0件です")
				objP.appendChild(msg)
				msgobj.appendChild(objP)
			}
		}
	}

	//通信失敗時の処理
	function error(){
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

	//ページングボタンの動的処理
	function pageButtonChanged(formNum){

		$("#pageButtonList").remove()
		var div = document.getElementById("pageButton")
		var ul = document.createElement("ul")
		ul.setAttribute("class","pagination")
		ul.setAttribute("id","pageButtonList")

		var li = document.createElement("li")
		li.setAttribute("class","page-item")
		if(formNum == 1){
			//prev ボタンを非アクティブ
			li.setAttribute("class","page-item disabled")
		}
		var button = document.createElement("button")
		var btntext = document.createTextNode("Prev")
		button.setAttribute("class","page-link")
		button.setAttribute("name","pageNum")
		button.appendChild(btntext)
		button.value = "prev"
		li.appendChild(button)
		ul.appendChild(li)

		if(formNum <= 6 || formNum >= 47){
			for(i=1;i<=6;i++){
				var li = document.createElement("li")
				li.setAttribute("class","page-item")

				var button = document.createElement("button")
				var btntext = document.createTextNode(String(i))
				button.setAttribute("class","page-link")
				button.setAttribute("name","pageNum")
				button.value = String(i)
				button.appendChild(btntext)

				li.appendChild(button)
				ul.appendChild(li)
			}
			var li = document.createElement("li")
			li.setAttribute("class","page-item disabled")

			var button = document.createElement("button")
			var btntext = document.createTextNode("...")
			button.setAttribute("class","page-link")
			button.setAttribute("name","pageNum")
			button.appendChild(btntext)

			li.appendChild(button)
			ul.appendChild(li)

			for(i=47;i<=50;i++){
				var li = document.createElement("li")
				li.setAttribute("class","page-item")

				var button = document.createElement("button")
				var btntext = document.createTextNode(String(i))
				button.setAttribute("class","page-link")
				button.setAttribute("name","pageNum")
				button.value = String(i)
				button.appendChild(btntext)

				li.appendChild(button)
				ul.appendChild(li)
			}

			var li = document.createElement("li")
			li.setAttribute("class","page-item")
			if(formNum == 50){
				//nextボタンを非アクティブ
				li.setAttribute("class","page-item disabled")
			}

			var button = document.createElement("button")
			var btntext = document.createTextNode("Next")
			button.setAttribute("class","page-link")
			button.setAttribute("name","pageNum")
			button.appendChild(btntext)
			button.value = "next"
			li.appendChild(button)
			ul.appendChild(li)

		}else{

		}

		div.appendChild(ul)
	}
	return false;//$(function()
})
