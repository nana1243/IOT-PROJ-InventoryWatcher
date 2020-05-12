<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600|Raleway:300,400,600&amp;subset=latin-ext">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-mousewheel/3.1.13/jquery.mousewheel.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.7.6/jquery.nicescroll.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<!-- table style  -->

<script>
	var itemList;// 데이터베이스에서 가져온 아이템 리스트 개수 
	var totalAddedItem = 0;// 버튼을 눌러서 추가된 아이템 개수 
	var SelectedItemArray = [];// 선택(미정)을 0으로 잡는다. 
	var SelectedItemMoney = [];
	var totalPrice = 0;
	var currentOrderID;
	var sendToDBData;
		
	//select option에서 선택된 아이템을 출력한다. 
	/*
	 $(document).ready(function(){
	 var tmpText = 'div.row.product';
	 var selectedItem;
	 for(var i = 0;i < totalAddedItem; i++ ){
	 tmpText += totalAddedItem.toString();// div.row.productn
	 selectedItem = $("#template > div.col-12.panel-body.basket-body > "+ tmpText +"select").val();//div.id_100 select		
	 //이 selecteItem을 바탕으로 
	 //여기서 div 코드를 붙여줘야 한다.
	 }
	 });
	 */
	//물건 수량을 더하는 함수
	function plusItem(pi, itemIndex) {
		SelectedItemArray[pi - 1]++;
		var tmpPi = "#" + pi + " > div.col-2.align-right > span";
		var tmpVal = $(tmpPi).html();// value인 것같은데 html 이 맞네  어이가 없다. 
		$(tmpPi).html((parseInt(tmpVal) + 1).toString());// 갯수 더해주기 
		totalPrice += SelectedItemMoney[itemIndex];
		document.querySelector("body > div > div > div > div > div > div.row > div > div.col-12.panel-footer.basket-footer > div > div > div").innerHTML = "총합: " +  totalPrice.toString();;
	 }
	//물건 수량을 빼는 함수 
	function minusItem(mi, itemIndex) {
			if (SelectedItemArray[mi - 1] <= 1) {
				alert("최소 수량은 한개입니다.");return;
			}
			SelectedItemArray[mi - 1]--;
			var tmpPi = "#" + mi + " > div.col-2.align-right > span";
			var tmpVal = $(tmpPi).html();// value인 것같은데 html 이 맞네  어이가 없다. 
			$(tmpPi).html((parseInt(tmpVal) - 1).toString());// 갯수 더해주기 
			totalPrice -= SelectedItemMoney[itemIndex];
			document.querySelector("body > div > div > div > div > div > div.row > div > div.col-12.panel-footer.basket-footer > div > div > div").innerHTML = "총합 : "+totalPrice.toString();
		}
	function destroyItem(di, itemIndex) {
		$("#" + di).remove(); //물건 항목지우기 
		//totalAddedItem--;//물건항목이 없어질 때마다 전역변수 totalAddedItem을 한개씩 빼준다. 그리고 selectedItem의 value를 0으로 초기화한다.
		if (totalPrice > 0) {
			totalPrice = totalPrice - SelectedItemMoney[itemIndex]	* (SelectedItemArray[di - 1] - 1);// di와 di-1로 구분 되는 것은 첫번 째 SelectedItemMoney는 선택이 있고 두번째 SelectedItemArray는 선택이 없기 때문이다.
		} else {
		}
		SelectedItemArray[di - 1] = 0;
		document.querySelector("body > div > div > div > div > div > div.row > div > div.col-12.panel-footer.basket-footer > div > div > div").innerHTML = "총합 : " + totalPrice.toString();
	}
	function myIngredientFunction(totalAddedItemIndex) {//n번째 항목과 n번째 아이템
		//아이템 추가되면 배열에 index 저장 ---> 수량을 지정하기 위해서  최소 수0은 0이다. 
		SelectedItemArray.push(1);//이거는 아이템 초기수량을 1로 잡은 것이다. 
		var y = document.getElementById("myIngredient").parentElement.nodeName;// div
		var z = $('#myIngredient').parent().attr('class');
		var DivOfmyIngredient = "div." + z.substring(0, 3) + '.'+ z.substring(4);
		console.log("itemList 는" + 	itemList);//itemList 중 받아온 n번 째를 표시해야 한다. 
		var myTmp = "#itemDivId" + totalAddedItemIndex + "> select option:selected";
		var indexOfItemList = $(myTmp).index();//아이템 배열의 인덱스 
		console.log("n번 째의 값은 "	+ DivOfmyIngredient.charAt(DivOfmyIngredient.length - 1));
		//DivOfmyIngredietn는 div.row.product1
		console.log(DivOfmyIngredient + "최종적으로 row product 이렇게 나와야 한다. ");
		var divsIndex = DivOfmyIngredient.charAt(DivOfmyIngredient.length - 1);
		var DivOfmyIngredientTmp = DivOfmyIngredient.substring(4, 7) + " "
				+ DivOfmyIngredient.substring(8, DivOfmyIngredient.length - 1);// row product 출력 --> row product를 해야 css가 작동한다.
		var myNewCode = "<div id ="+ totalAddedItemIndex   +" class =\"" + DivOfmyIngredientTmp + "\">";
		/*myNewCode는 물건의 사진, 아이디 , 수량, 가격을 표현 한 것이다.*/
		//버튼에 div row product 의 index를 주었다. 
		myNewCode += "<div class='col-2 product-image'>";
		myNewCode += totalAddedItemIndex;
		//myNewCode += "<img src=\""+ itemList[indexOfItemList].IngImgName+"\">";
		myNewCode += "</div>";
		myNewCode += "<div class= \"col-5\">";
		myNewCode += itemList[indexOfItemList].IngName+ "<button onclick=\"plusItem("+totalAddedItemIndex+","+indexOfItemList+")\">+</button>";
		myNewCode += "<button onclick=\"minusItem("+totalAddedItemIndex+","+indexOfItemList+")\">-</button>";
		//myNewCode += "<button onclick=\"minusItem(" + totalAddedItemIndex + ")\">-</button>";
		myNewCode += "<button onclick= \"destroyItem("+totalAddedItemIndex+","+indexOfItemList+")\">물품삭제</button></div>";//아이템 지우기 
		myNewCode += "<div class=\"col-2 align-right\">";
		//여기서 버튼을 눌러서 수량을 증감한다. 
		myNewCode += "<span class=\"sub\">" + "1" + "</span>"+ itemList[indexOfItemList].IngUnit;
		myNewCode += "</div>";
		myNewCode += "<div class=\"col-3 align-right\">";
		myNewCode += "<span class=\"sub\">" + "$" + "</span>"+ itemList[indexOfItemList].IngPrice;
		myNewCode += "</div>";
		myNewCode += "</div>";
		DivOfmyIngredient = DivOfmyIngredient.substring(0,DivOfmyIngredient.length - 1)+totalAddedItemIndex;
		var tmp = $(DivOfmyIngredient).replaceWith(myNewCode);
	}
	//디비에서 ingredient 정보를 가져오는 ajax
	function getData() {
		$.ajax({
			url : 'orderData.top',
			success : function(data) {
				//여기서 데이터를 받아온다
				console.log("data는 " + data + "data type은 " + typeof (data)+ " data length 는" + data.length);
				console.log(data);
				itemList = data;
				SelectedItemMoney = [];
				for (var i = 0; i < data.length; i++) {
					console.log("가격은  " + data[i].IngPrice);
					SelectedItemMoney.push(data[i].IngPrice);
				}
				console.log(SelectedItemMoney);
			},
			error : function(request, status, error) {
				console.log("error!" + request.status + "\n\n message = "
						+ request.responseText + " \n\nerror = " + error);
			}
		});
	};
	function orderStatusData(chainOrHQ) {
		$.ajax({
					url : 'orderStatusData.top',
			        type: "get",
			        data : { "chainOrHQ" : chainOrHQ },
					success : function(data) {
						//디비에 보낼 데이터를 저장한다.
						myObject = {};
						sendToDBData = data;
						console.log(data + " data length " + data.length
								+ "data type is " + typeof (data));
						// draw graph;
						var myTable = "";
						myTable += "<div id=\"rowC\" class=\"col-xs-8 col-xs-offset-2 well\">";
						myTable += "<table class=\"table table-scroll table-striped\">";
						myTable += "<thead><tr><th>#</th><th>HQ</th><th>CHAIN</th><th>container ID</th><th>ID</th>";
						myTable += "<th>재료 이름 </th><th>재료 가격</th><th>재료 단위</th><th>재료 무게</th><th>수량</th><th>주문 상태</th></tr></thead><tbody id = \"myTable\"><tbody></table></div>";
						$('#template > div.col-12.panel-body.basket-body').append(myTable);
						var myTemp;
						myTemp += "";
						for (var i = 0; i < data.length; i++) {
							if(i == 0){
								currentOrderID = data[i].OrderID;
							}
							myTemp += "<tr>";
							myTemp += "<td>" + i + 1 + "</td>";
							myTemp += "<td>" + data[i].HqName + "</td>";
							myTemp += "<td>" + data[i].ChainName + "</td>";
							myTemp += "<td>" + data[i].ConID + "</td>";
							myTemp += "<td>" + data[i].UserName + "</td>";
							myTemp += "<td>" + data[i].IngName + "</td>";
							myTemp += "<td>" + data[i].IngPrice + "</td>";
							myTemp += "<td>" + data[i].IngUnit + "</td>";
							myTemp += "<td>" + data[i].IngWeight + "</td>";
							myTemp += "<td>" + data[i].IngQuantity + "</td>";
							myTemp += "<td>" + data[i].OrderState+ "</td>";
							myTemp += "</tr>";
							totalPrice += data[i].IngPrice * data[i].IngQuantity;
							document.querySelector('body > div > div > div > div > div > div.row > div > div.col-12.panel-footer.basket-footer > div > div > div').innerHTML = "총합 : "+ totalPrice.toString();  
						}
						$('#myTable').append(myTemp);
						//alert(currentOrderID);

						document.querySelector("#template > div.col-12.panel-header.basket-header > div:nth-child(1) > div.col-6.order-number.align-right > span.emphasized").innerHTML = currentOrderID;
						//총합 설정 
					},
					error : function(request, status, error) {
						console.log("error!" + request.status
								+ "\n\n message = " + request.responseText
								+ " \n\nerror = " + error);
					}
				});
	};
	//물건 항목을 더하는 함수 
	function addItem() {
		//여기서 html 코드를 넣어야 한다.
		//ajax로 n개의 ingredient item을 가져온다.  	
		var temp = '';
		getData();//
		if(itemList.length == 0){
			setTimeout(
					function() {
						totalAddedItem++;
						// 2초 후 작동해야할 코드   ---> 이것을 안하면 AJAX가 값을 받아오기 전에 itemList가 값을 뿌린다. 
						temp += '<div id = "itemDivId'+  totalAddedItem   +'"class="row product'+ totalAddedItem + '">';
						temp += '<select id="myIngredient" onchange="myIngredientFunction('
								+ totalAddedItem + ')">';//size="'+ itemList.length +'" 여기서 totalAddedItem을 넘겨준다. 
						$(itemList).each(
								function(idx, item) {
									console.log(item.IngName + "\n");//
									temp += '<option value="'+item.IngName + '">'
											+ item.IngName + '</option>';
								});
						temp += '</select>';
						temp += '</div>';
						$('#template > div.col-12.panel-body.basket-body').append(temp);
					}, 2000);
		}else if(itemList.length != 0){
			totalAddedItem++;
			// 2초 후 작동해야할 코드   ---> 이것을 안하면 AJAX가 값을 받아오기 전에 itemList가 값을 뿌린다. 
			temp += '<div id = "itemDivId'+  totalAddedItem   +'"class="row product'+ totalAddedItem + '">';
			temp += '<select id="myIngredient" onchange="myIngredientFunction('+ totalAddedItem + ')">';
			//size="'+ itemList.length +'" 여기서 totalAddedItem을 넘겨준다. 
			$(itemList).each(
					function(idx, item) {
						console.log(item.IngName + "\n");//
						temp += '<option value="'+item.IngName + '">'
								+ item.IngName + '</option>';
					});
			temp += '</select>';
			temp += '</div>';
			$('#template > div.col-12.panel-body.basket-body').append(temp);
		}
	};
	$(document).ready(function() {
		getData();
		//cafe_TOP_hq;
		var loginId = "${loginId}";
		///기업용인지 사용자용인지에 따라서 뽑는 데이터 달라진다. 
		var chainOrHq = 0;
		if(loginId.includes("chainID")){// 체인의 가맹점용
			chainOrHq = 0;
		}else if(loginId.includes("hq")){// 체인의 헤드쿼터용
			chainOrHq = 1;
		}
		///오더 테이블을 넣는다. 
		orderStatusData(chainOrHq);
		$('.emphasized').text(currentOrderID);
		//alert($('emphasized').text());
		
		$('[data-toggle="tooltip"]').tooltip();
		var template = $('#template').html();
		Mustache.parse(template);
		var rendered = Mustache.render(template, get_basket());
		$('#template').html(rendered);
		if ($('.basket-body').hasScrollBar()) {
			$('.column-titles').addClass('fix-overflow');
			$('.basket-body').niceScroll({
				autohidemode : false,
				cursorcolor : "#ffffff",
				cursorborder : "1px solid #D0D0D0",
				cursorborderradius : "0",
				background : "#ffffff"
			});
		} else {
			$('.column-titles').removeClass('fix-overflow');
		}
		$('.card-expiration').datepicker({
			format : "mm/yyyy",
			startView : "months",
			minViewMode : "months"
		});
	});
	function get_basket() {
	}

	//https://stackoverflow.com/questions/4814398/how-can-i-check-if-a-scrollbar-is-visible
	(function($) {
		$.fn.hasScrollBar = function() {
			return this.get(0).scrollHeight > this.height();
		}
	})(jQuery);
</script>
<style>
#rowC { //가로 스크롤 
    white-space: nowrap;/* 가로스크롤시 중요한 속성 */
    overflow-x: auto;
    overflow-y: hidden;
    padding: 10px 10px 5px;
    background: #efefef;
    width: 600px;
}

html, body {
	height: 100%;
	color: black;
	/* font-family: 'Barlow', sans-serif; */
	/* font-family: 'Roboto Condensed', sans-serif; */
	font-family: 'Open Sans', sans-serif;
	font-weight: 400;
}

body {
	font-size: 62.5%;
}

body {
	/*
	background:
		url(https://images.unsplash.com/photo-1462899006636-339e08d1844e?auto=format&fit=crop&w=1950&q=80&ixid=dW5zcGxhc2guY29tOzs7Ozs%3D)
		no-repeat center center fixed;*/
	background-size: cover;
}

.main-wrapper {
	border-radius: 15px 15px 15px 15px;
	-moz-border-radius: 15px 15px 15px 15px;
	-webkit-border-radius: 15px 15px 15px 15px;
	border: none;
	-webkit-box-shadow: 0px 20px 10px 10px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 0px 20px 10px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 0px 20px 10px 10px rgba(0, 0, 0, 0.1);
}

.basket-header {
	border-radius: 15px 0 0 0;
	-moz-border-radius: 15px 0 0 0;
	-webkit-border-radius: 15px 0 0 0;
	padding-left: 25px !important;
}

.creditcard-header {
	border-radius: 0 15px 0 0;
	-moz-border-radius: 0 15px 0 0;
	-webkit-border-radius: 0 15px 0 0;
	padding-left: 35px !important;
}

.panel-wrapper {
	
}

.panel-header {
	background: #166D9A;
	height: 80px;
	padding: 15px 20px 0 20px;
}

.panel-wrapper .basket-header .column-titles {
	color: #A2C6DD;
	padding: 0;
	margin: 0;
	/* font-family: 'Anton', sans-serif; */
	display: none;
	visibility: hidden;
}

.fix-overflow {
	padding-right: 5px !important;
}

.panel-wrapper .basket-body {
	overflow-x: hidden;
	overflow-y: auto;
}

.panel-wrapper .creditcard-body {
	padding: 30px 40px 0 40px;
}

.panel-wrapper .panel-body {
	font-weight: 400;
	font-size: 1.2em;
	outline: none !important;
	min-height: 350px;
	max-height: 350px;
}

.basket-body {
	background: #F9F9F9;
}

.creditcard-body {
	background: white;
}

.basket-body .row.product {
	margin: 5px 0 5px 0;
	padding: 5px 0 5px 0;
	border-bottom: solid 1px #eeeeee;
}

.basket-body .row.product div {
	color: #777879;
	padding: 0 10px 0 10px;
}

.basket-body .row.product .product-image {
	
}

.product-image img {
	-o-object-fit: contain;
	object-fit: contain;
	width: 100%;
	min-width: 100%;
	max-width: 100%;
	max-height: 80px;
}

.card-wrapper {
	height: 100%;
}

.padding-top-10 {
	padding-top: 10px !important;
}

.padding-top-20 {
	padding-top: 20px !important;
}

.padding-horizontal-40 {
	padding: 0 40px 0 40px;
}

.align-right {
	text-align: right;
}

.align-center {
	text-align: center;
}

.emphasized {
	/* font-family: 'Anton', sans-serif; */
	/* font-family: 'Roboto Condensed', sans-serif; */
	/* font-family: 'Raleway', sans-serif; */
	font-family: 'Open Sans', sans-serif;
	font-weight: 600;
	font-size: 1.6em;
	color: white;
}
.description {
	/* font-family: 'Anton', sans-serif; */
	/* font-family: 'Roboto Condensed', sans-serif; */
	/* font-family: 'Raleway', sans-serif; */
	font-family: 'Open Sans', sans-serif;
	font-weight: 400;
	font-size: 1.2em;
	color: #A2C6DD;
}

.panel-footer {
	padding-top: 10px;
	height: 150px;
}

.basket-footer {
	background: #166D9A;
	border-radius: 0 0 0 15px;
	-moz-border-radius: 0 0 0 15px;
	-webkit-border-radius: 0 0 0 15px;
}

.basket-footer .title, .basket-footer .subtitle {
	
}

.creditcard-footer {
	background: white;
	border-radius: 0 0 15px 0;
	-moz-border-radius: 0 0 15px 0;
	-webkit-border-radius: 0 0 15px 0;
}

.basket-footer .row .subtitle, .basket-footer .row .title {
	
}

.panel-footer hr {
	margin: 3px 0 3px 0;
	display: block;
	height: 1px;
	border: 0;
	border-top: 1px solid #197fb3;
	padding: 0;
}

.panel-footer button {
	border: solid 1px #166D9A;
	background: #166D9A;
	font-family: 'Open Sans', sans-serif;
	font-weight: 600;
	color: white;
	font-size: 1.3em;
	text-transform: uppercase;
	padding: 10px 15px 11px 15px;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
}

.panel-footer button:hover {
	cursor: pointer;
}

button.cancel {
	background: white;
	color: #166D9A;
}

button.cancel:hover {
	background: #ff0000;
	border-color: #ff0000;
	color: white;
}
button.confirm:hover {
	background: #00b300;
	border-color: #00b300;
	color: white;
}
.dive {
	margin-top: 5px;
}
.sub {
	font-size: 75%;
	color: #aaaaaa;
}
.very {
	font-size: 2.2em;
}
.creditcard-body form {
	font-size: 1.3em;
}
.creditcard-body form i.fa {
	margin-right: 10px;
	color: #166D9A;
}
.creditcard-body form fieldset {
	border-bottom: dotted 2px #D0D0D0;
	margin-bottom: 25px;
}
.creditcard-body form input {
	border: none;
	font-weight: 600;
	color: #555555;
	width: 85%;
	outline: none;
}
.creditcard-body form input::placeholder {
	color: #D0D0D0;
}
.creditcard-body form label {
	color: #aaaaaa;
}
.additional {
	font-weight: 300;
	font-size: 80%;
}
.fa-info-circle {
	color: #aaaaaa !important;
}
span.month.focused.active {
	background: #166D9A !important;
	background-image: none !important;
}

@media ( max-width : 992px) {
}

@media ( max-width : 767px) {
	.basket-header {
		border-radius: 15px 15px 0 0;
		-moz-border-radius: 15px 15px 0 0;
		-webkit-border-radius: 15px 15px 0 0;
	}
	.basket-footer {
		background: #166D9A;
		border-radius: 0;
		-moz-border-radius: 0;
		-webkit-border-radius: 0;
	}
	.creditcard-header {
		border-radius: 0;
		-moz-border-radius: 0;
		-webkit-border-radius: 0;
	}
	.creditcard-footer {
		border-radius: 0 0 15px 15px;
		-moz-border-radius: 0 0 15px 15px;
		-webkit-border-radius: 0 0 15px 15px;
	}
}
</style>
</head>
<body>
	<div class="container-fluid background">
		<div class="row padding-top-20">
			<div
				class="col-12 col-sm-12 col-md-10 col-lg-10 col-xl-8 offset-md-1 offset-lg-1 offset-xl-2 padding-horizontal-40">
				<div class="row">
					<div class="col-12 main-wrapper">
						<div class="row">
							<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
								<div id="template" class="row panel-wrapper">
									<div class="col-12 panel-header basket-header"
										style="height: 100px !important">
										<div class="row">
											<div class="col-6 basket-title">
												<span class="description">주문자 : ${loginId}</span><br> <span
													class="emphasized"></span>
											</div>
											<div class="col-6 order-number align-right">
												<span class="description">order ID</span><br> <span
													class="emphasized">order ID 가져와야 함 </span>
											</div>
										</div>
										<div class="row"
											style="height: 40px !important; width: 425px !important;">
											<div class="col-2 align-left">
												<span></span>
											</div>
											<div class="col-6 align-left">
												<span></span>
											</div>
											<div class="col-3 align-left">
												<span></span>
											</div>
											<div class="col-1 align-left">
												<span></span>
											</div>
										</div>
									</div>
									<div class="col-12 panel-body basket-body" ">


									</div>
								</div>
								<div class="col-12 panel-footer basket-footer">
									<hr>
									<div class="row">
										<div class="col-8 align-right description">
										
											<input type="button" onclick="addItemToContainer()" style="color: red; float: left; "/>
											<div class="dive">총합 :</div>
										</div>
									</div>
									<hr>
								</div>
							</div>
						</div>
						<script>
							function addItemToContainer(){
								window.open('', '_self', ''); window.close();
							}
						</script>
						<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
							<div class="row panel-wrapper">
								<div class="col-12 panel-footer creditcard-footer">
									<div class="row">
										<div class="col-12 align-right">
											<button class="confirm">주문하기</button>

											&nbsp;
											<button class="cancel"
												onclick="window.open('', '_self', ''); window.close();">
												취소하기</button>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
function InsertSelectedData(){
	//sendToDBData //이게 보낼 데이터 가진 javascript object
	//orderID 넘기고 
	var numItems = $('.row.product').length;
	//alert("추가된 아이템의 갯수는 " + numItems + " 입니다. ");
	/*
	var myObject = {};
	alert(sendToDBData[0].orderDetailID);
	for(var i = 0 ; i < sendToDBData.length; i++){
		myObject[i.toString()] = sendToDBData[i].orderDetailID;
	}
	console.log("myObject 는" + myObject);
	sendDataToDB(myObject, sendToDBData.length);*/
}
function ReadSelectedData(){
	//sendToDBData //이게 보낼 데이터 가진 javascript object
	var myObject = {};
	//alert(sendToDBData[0].orderDetailID);
	for(var i = 0 ; i < sendToDBData.length; i++){
		myObject[i.toString()] = sendToDBData[i].orderDetailID;
	}
	console.log("myObject 는" + myObject);
	sendDataToDB(myObject, sendToDBData.length);
}
function sendDataToDB(myObject, myObjectLength) {
	//alert("길이는" + myObjectLength);
	var data = JSON.stringify(myObject);
	$.ajax({ 
		type : "post",
		dataType : 'JSON',
		data : {
			loadProds : myObjectLength,
			data1 : data,
		},
		url : 'sendDataToDB.top',
		success : function(data) {
			//alert("DB에 보내는 것이 성공하였습니다.");
			display(data);
		},
		error : function(request, status, error) {
			alert("code = " + request.status + "\n message = "
					+ request.responseText + " error = " + error); // 실패 시 처리
		}
	});
	
};
$( ".confirm" ).click(function() {
	//주문 하기 버튼 
	//0원 이면 주문 불가, 팝업으로 물어보기 
	if (confirm("정말로 주문하시겠습니까?")) {
		if(totalPrice == 0 ){// 주문 불가 
			alert("최소 수량을 선택해주세요 ");
		}else{//주문가능 
			window.open('', '_self', ''); window.close();
			
			ReadSelectedData();
			InsertSelectedData();
			//ajax로 컨트롤러로 서버에 데이터 보내기 
		}
	} else {
		  txt = "You pressed Cancel!";
	}
});
</script>
</body>
</html>