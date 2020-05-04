<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>TOP Smart Inventory Management System</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />
<link rel="icon" href="assets/img/icon.ico" type="image/x-icon" />

<!-- Fonts and icons -->
<script src="assets/js/plugin/webfont/webfont.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>
	WebFont.load({
		google : {
			"families" : [ "Open+Sans:300,400,600,700" ]
		},
		custom : {
			"families" : [ "Flaticon", "Font Awesome 5 Solid",
					"Font Awesome 5 Regular", "Font Awesome 5 Brands" ],
			urls : [ '../assets/css/fonts.css' ]
		},
		active : function() {
			sessionStorage.fonts = true;
		}
	});
</script>

<!-- Fonts and icons -->
<script src="assets/js/plugin/webfont/webfont.min.js"></script>
<script>
	WebFont.load({
		google : {
			"families" : [ "Open+Sans:300,400,600,700" ]
		},
		custom : {
			"families" : [ "Flaticon", "Font Awesome 5 Solid",
					"Font Awesome 5 Regular", "Font Awesome 5 Brands" ],
			urls : [ 'assets/css/fonts.css' ]
		},
		active : function() {
			sessionStorage.fonts = true;
		}
	});
	
	
	
	
	


	var popupCheck = 5;
	
	var targetObj = {};
	var targetProxy = new Proxy(targetObj, {
	  set: function (target, key, value) {
	      //console.log(`${key} set to ${value}`);
	      location.href = "/sendToContainer.top";
	      target[key] = value;
	      return true;
	  }
	});
	
	//targetProxy.hello_world = "test";
	function getData(value) {
		$.ajax({
			url : '/orderData.top',
			success : function(data) {
				//여기서 데이터를 받아온다
				itemList = data;
				if (value == 1) {
					return data;
				}
			}
		});
	};
	// 자식으로부터 메시지 수신
	function receiveMsgFromChild( e ) {
	    console.log( '자식으로부터 받은 메시지 ' +  e.data + " printed" );
	}
	function popup() {
		console.log("test 05");
		var url = "popupOrder.top";//../top/view/main/popupOrder.jsp 
		//popupOrder.top 하면 매핑이 된다. 
		var name = "popup test";
		var option = "width = 700, height =1200, top = 100, left = 200, location = no";
		window.open(url, name, option);
	}
	

</script>

</script>

<!-- CSS Files -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/azzara.min.css">

<!-- CSS Just for demo purpose, don't include it in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
</head>
<body>
	<div class="wrapper">

		<c:choose>
			<c:when test="${loginId == null }">
				<jsp:include page="../user/login.jsp" />
			</c:when>

			<c:otherwise>
				<jsp:include page="header.jsp" />
				<jsp:include page="sidebar.jsp" />

				<!--  Center Page Here! -->
				<div class="main-panel">
					<div class="content">
						<div class="page-inner">
							<!-- Visual Analysis START! -->
							<c:choose>
								<c:when test="${AllChainsVisualAnalysis != null }">
									<jsp:include page="${AllChainsVisualAnalysis }.jsp" />
								</c:when>
							</c:choose>
							<!-- Visual Analysis END! -->

							<c:choose>
								<c:when test="${center != null }">
									<jsp:include page="${center }.jsp" />

								</c:when>

							</c:choose>


						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>


<!--   Core JS Files   -->
<script src="assets/js/core/jquery.3.2.1.min.js"></script>
<script src="assets/js/core/popper.min.js"></script>
<script src="assets/js/core/bootstrap.min.js"></script>

<!-- jQuery UI -->
<script src="assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script
	src="assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

<!-- jQuery Scrollbar -->
<script src="assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

<!-- Moment JS -->
<script src="assets/js/plugin/moment/moment.min.js"></script>

<!-- Chart JS -->
<script src="assets/js/plugin/chart.js/chart.min.js"></script>

<!-- jQuery Sparkline -->
<script src="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

<!-- Chart Circle -->
<script src="assets/js/plugin/chart-circle/circles.min.js"></script>

<!-- Datatables -->
<script src="assets/js/plugin/datatables/datatables.min.js"></script>

<!-- Bootstrap Notify -->
<script src="assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

<!-- Bootstrap Toggle -->
<script src="assets/js/plugin/bootstrap-toggle/bootstrap-toggle.min.js"></script>

<!-- jQuery Vector Maps -->
<script src="assets/js/plugin/jqvmap/jquery.vmap.min.js"></script>
<script src="assets/js/plugin/jqvmap/maps/jquery.vmap.world.js"></script>

<!-- Google Maps Plugin -->
<script src="assets/js/plugin/gmaps/gmaps.js"></script>

<!-- Sweet Alert -->
<script src="assets/js/plugin/sweetalert/sweetalert.min.js"></script>

<!-- Azzara JS -->
<script src="assets/js/ready.min.js"></script>

<!-- Azzara DEMO methods, don't include it in your project! -->
<script src="assets/js/setting-demo.js"></script>
<script src="assets/js/demo.js"></script>
</html>