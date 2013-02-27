<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computers database</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/main.css">
</head>
<body>
	<header class="topbar">
	<h1 class="fill">
		<a href="/ProjectPlay2/Computers"> Kevin database </a>
	</h1>
	</header>
	<section id="main">
		<h1>Edit computer</h1>
		<form action="/ProjectPlay2/SaveComputer" method="GET">
			<fieldset>
				<div class="clearfix ">
					<label for="name">Computer name</label>
					<div class="input">
						<input type="text" id="name" name="name"
							value="${computer.nameComputer}"> <span
							class="help-inline">Required</span>
						<input type="hidden" id="id" name="id"
							value="${computer.idComputer}">
					</div>
				</div>
				
				
				<c:choose>
					<c:when test="${introducedError==1}">
						<div class="clearfix error ">
							<label for="introduced">Introduced date</label>
							<div class="input">

								<input type="text" id="introduced" name="introduced"
									value="${introducedValue}"> <span
									class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="clearfix ">
							<label for="introduced">Introduced date</label>
							<div class="input">

								<input type="text" id="introduced" name="introduced"
									value="${computer.introducedDate}"> <span
									class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
							</div>
						</div>
					</c:otherwise>
				</c:choose>



				<c:choose>
					<c:when test="${discontinuedError==1}">
						<div class="clearfix error ">
							<label for="discontinued">Discontinued date</label>
							<div class="input">

								<input type="text" id="discontinued" name="discontinued"
									value="${discontinuedValue}"> <span class="help-inline">Date
									(&#x27;yyyy-MM-dd&#x27;)</span>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="clearfix ">
							<label for="discontinued">Discontinued date</label>
							<div class="input">

								<input type="text" id="discontinued" name="discontinued"
									value="${computer.dscountedDate}"> <span
									class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="clearfix ">
					<label for="company">Company</label>
					<div class="input">

						<select id="company" name="company">
							<c:if test="${computer.company.idCompany==null or computer.company.idCompany==0}">
								<option class="blank" value="">-- Choose a company --</option>
							</c:if>				            
							<c:forEach var="comp" items="${company}">
								<c:choose>
									<c:when test="${comp.idCompany==computer.company.idCompany}">
										<option value="${comp.idCompany}" selected>${comp.nameCompany}</option>
									</c:when>
									<c:otherwise>
										<option value="${comp.idCompany}">${comp.nameCompany}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>

						</select> <span class="help-inline"></span>
					</div>
				</div>



			</fieldset>

			<div class="actions">
				<input type="submit" value="Save this computer" class="btn primary">
				or <a href="/ProjectPlay2/Computers" class="btn">Cancel</a>
			</div>


		</form>




		<form action="/ProjectPlay2/Delete?id=${computer.idComputer}" method="POST" class="topRight">
			<input type="submit" value="Delete this computer" class="btn danger">
		</form>



	</section>

</body>
</html>

