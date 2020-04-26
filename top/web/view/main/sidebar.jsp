<!-- Sidebar START -->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="sidebar">
	<div class="sidebar-background"></div>
	<div class="sidebar-wrapper scrollbar-inner">
		<div class="sidebar-content">
			<div class="user">
				<div class="avatar-sm float-left mr-2">
					<img src="assets/img/profile.jpg" alt="..."
						class="avatar-img rounded-circle">
				</div>
				<div class="info">
					<a data-toggle="collapse" href="#collapseExample"
						aria-expanded="true"> <span> Hizrian <span
							class="user-level">Administrator</span> <span class="caret"></span>
					</span>
					</a>
					<div class="clearfix"></div>

					<div class="collapse in" id="collapseExample">
						<ul class="nav">
							<c:choose>
								<c:when test="${who == 'hq'}">
									<li><a href="addAddr.top"> <span class="link-collapse">
												addAddr</span>
									</a></li>
									<li><a href="admin.top"> <span class="link-collapse">
												adminpage</span>
									</a></li>

									<li><a href="update.top"> <span class="link-collapse">
												update my page</span>
									</a></li>

								</c:when>
								<c:otherwise>
									<li><a href="apply.top"> <span class="link-collapse">
												apply user</span>
									</a></li>

								</c:otherwise>
							</c:choose>
							<li><a href="logout.top"> <span class="link-collapse">
										logout</span>
							</a></li>

						</ul>
					</div>
				</div>
			</div>


			<!-- MENU START HERE -->
			<ul class="nav">

				<!-- HOME -->
				<li class="nav-item active"><a href="index.html"> <i
						class="fas fa-home"></i>
						<p>HOME</p> <!-- <span class="badge badge-count">5</span> -->
				</a></li>

				<!-- Menu -->
				<li class="nav-section"><span class="sidebar-mini-icon">
						<i class="fa fa-ellipsis-h"></i>
				</span>
					<h4 class="text-section">Menu</h4></li>

				<!-- Inventory Management -->
				<li class="nav-item"><a data-toggle="collapse" href="#base">
						<i class="fas fa-layer-group"></i>
						<p>Inventory</p>
				</a></li>


				<!-- Delivery Status -->
				<li class="nav-item"><a data-toggle="collapse" href="#forms">
						<i class="fas fa-pen-square"></i>
						<p>Delivery Status</p>
				</a></li>

				<!-- Chain Access Management -->
				<li class="nav-item"><a data-toggle="collapse" href="#tables">
						<i class="fas fa-table"></i>
						<p>Chain Access</p>
				</a></li>

				<!-- Menu Management -->
				<li class="nav-item"><a data-toggle="collapse" href="#maps">
						<i class="fas fa-map-marker-alt"></i>
						<p>Menu</p>
				</a></li>
				<!-- Container Management -->
				<li class="nav-item"><a data-toggle="collapse" href="#submenu"
					class="collapsed" aria-expanded="false"> <i
						class="far fa-chart-bar" aria-expanded="false"></i>
						<p>
							container</p> <span class="caret"></span></a>
					<div class="collapse" id="submenu" style>
						<ul>
							<li class="submenu"><a data-toggle="collapse"
								href="#subnav1" class=""
								onclick="location.href='containerRegisterWizard.top'"> <span
									class="sub-item">而⑦뀒?대꼫 異붽?</span>
							</a></li>
							<li class="submenu"><a data-toggle="collapse"
								href="#subnav1" class=""
								onclick="location.href='showContainerUpdateList.top'"> <span
									class="sub-item">而⑦뀒?대꼫 ?섏젙</span>
							</a></li>
						</ul>
					</div></li>


				<!-- A/S Center -->
				<li class="nav-item"><a href="widgets.html"> <i
						class="fas fa-desktop"></i>
						<p>A/S Center</p>
				</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- Sidebar END -->