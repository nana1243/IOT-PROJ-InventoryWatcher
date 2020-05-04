<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<style>
* {
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
}

body {
	font-family: Helvetica;
	-webkit-font-smoothing: antialiased;
}

h2 {
	text-align: center;
	font-size: 18px;
	text-transform: uppercase;
	letter-spacing: 1px;
	color: white;
	padding: 30px 0;
}

/* Table Styles */
.table-wrapper {
	margin: 10px 70px 70px;
	box-shadow: 0px 35px 50px rgba(0, 0, 0, 0.2);
	width: 100%;
}

.fl-table {
	border-radius: 5px;
	font-size: 12px;
	font-weight: normal;
	border: none;
	border-collapse: collapse;
	width: 100%;
	max-width: 100%;
	white-space: nowrap;
	background-color: white;
}

.fl-table td, .fl-table th {
	text-align: center;
	padding: 8px;
}

.fl-table td {
	border-right: 1px solid #f8f8f8;
	font-size: 12px;
}

.fl-table thead th {
	color: #ffffff;
	background: #4FC3A1;
}

.fl-table thead th:nth-child(odd) {
	color: #ffffff;
	background: #324960;
}

.fl-table tr:nth-child(even) {
	background: #F8F8F8;
}

/* Responsive */
@media ( max-width : 767px) {
	.fl-table {
		display: block;
		width: 100%;
	}
	.table-wrapper:before {
		content: "Scroll horizontally >";
		display: block;
		text-align: right;
		font-size: 11px;
		color: white;
		padding: 0 0 10px;
	}
	.fl-table thead, .fl-table tbody, .fl-table thead th {
		display: block;
	}
	.fl-table thead th:last-child {
		border-bottom: none;
	}
	.fl-table thead {
		float: left;
	}
	.fl-table tbody {
		width: auto;
		position: relative;
		overflow-x: auto;
	}
	.fl-table td, .fl-table th {
		padding: 20px .625em .625em .625em;
		height: 60px;
		vertical-align: middle;
		box-sizing: border-box;
		overflow-x: hidden;
		overflow-y: auto;
		width: 120px;
		font-size: 13px;
		text-overflow: ellipsis;
	}
	.fl-table thead th {
		text-align: left;
		border-bottom: 1px solid #f7f7f9;
	}
	.fl-table tbody tr {
		display: table-cell;
	}
	.fl-table tbody tr:nth-child(odd) {
		background: none;
	}
	.fl-table tr:nth-child(even) {
		background: transparent;
	}
	.fl-table tr td:nth-child(odd) {
		background: #F8F8F8;
		border-right: 1px solid #E6E4E4;
	}
	.fl-table tr td:nth-child(even) {
		border-right: 1px solid #E6E4E4;
	}
	.fl-table tbody td {
		display: block;
		text-align: center;
	}
}
</style>
<script>
var orderStatusData;

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
						
					}
					$('#myTable').append(myTemp);  
				},
				error : function(request, status, error) {
					console.log("error!" + request.status
							+ "\n\n message = " + request.responseText
							+ " \n\nerror = " + error);
				}
			});
};


function getDataDrawTable() {
	$.ajax({
		url:'orderStatusData.top',
		success:function(data){
			orderStatusData = data;
			console.log(data + " data length " + data.length + "data type is " + typeof(data));
			// draw graph;
			var myTemp;
			myTemp += "";
			for(var i =  0; i < data.length; i++){
				myTemp += "<tr>";
				myTemp += "<td>"+ data[i].OrderChainID +"</td>";
				myTemp += "<td>"+ data[i].OrderOrderID +"</td>";
				myTemp += "<td>"+ data[i].OrderPayment +"</td>";
				myTemp += "<td>"+ data[i].OrderDate +"</td>";
				myTemp += "<td>"+ data[i].OrderDeliveryState +"</td>";
				myTemp += "<td>"+ data[i].OrderOrderState +"</td>";
				myTemp += "</tr>";
			
			}      
			$('#myTable').append(myTemp);  
		}, 
		error : function(request, status, error){
			console.log("error!" + request.status + "\n\n message = " + request.responseText + " \n\nerror = " + error );
		}
	});
};
$( document ).ready(function() {
	var loginId = "${loginId}";
	///기업용인지 사용자용인지에 따라서 뽑는 데이터 달라진다. 
	var chainOrHq = 0;
	if(loginId.includes("chainID")){// 체인의 가맹점용
		chainOrHq = 0;
	}else if(loginId.includes("hq")){// 체인의 헤드쿼터용
		chainOrHq = 1;
	}
	orderStatusData(chainOrHq);
	//getDataDrawTable();
});

</script>
<h1 class="page-title">Order Status</h1>
<div class="card">
	<div class="card-body">
		<div class="row">
			<div class="col-7 col-md-8" style = "width : 100% !important; max-width: 100%; flex : 0 0 100% !important ">
				<form class="navbar-left navbar-form nav-search mr-md-3" style = "width : 100%">
					<div class="input-group">
						<div class="input-group-prepend">
							<button type="submit" class="btn btn-search pr-1">
								<i class="fa fa-search search-icon"></i>
							</button>
						</div>
						<input type="text" placeholder="Search ..." class="form-control">
					</div>
				</form>
			</div>
			<div class="table-wrapper">
				<table class="fl-table">
					<thead>
						<tr>
							<th>순번</th>
							<th>헤드쿼터</th>
							<th>체인이름</th>
							<th>컨테이너ID</th>
							<th>사용자명</th>
							<th>재료명</th>
							<th>가격</th>
							<th>재료 단위</th>
							<th>재료 무게</th>
							<th>재료 수량</th>
							<th>주문 상태</th>
						</tr>
					</thead>
					<tbody id = "myTable">
					<tbody>
				</table>
			</div>
		</div>
	</div>
</div>
