<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<a href="/Play2v2/Computers.html"> Kevin database </a>
		</h1>
	</header>
	<section id="main">
		<h1>Edit computer</h1>
		<form:form action="/Play2v2/SaveComputer.html" method="GET"
			commandName="computer">
			<fieldset>
				<div
					class="clearfix <c:if test="${!empty result.getFieldError('nameComputer')}">error</c:if>">
					<label for="name">Computer name</label>
					<div class="input">
						<form:input path="nameComputer" />
						<c:if test="${!empty result.getFieldError('nameComputer')}"><div>Required</div></c:if>
					</div>
					<input type="hidden" id="id" name="id"
							value="${computer.idComputer}">
				</div>

				<div
					class="clearfix <c:if test="${!empty result.getFieldError('introducedDate')}">error</c:if>">
					<label for="introduced">Introduced date</label>
					<div class="input">
						<form:input path="introducedDate" />
						<c:if test="${!empty result.getFieldError('introducedDate')}"><div>La date doit être en "yyyy-MM-dd"</div></c:if>
					</div>
				</div>

				<div
					class="clearfix <c:if test="${!empty result.getFieldError('dscountedDate')}">error</c:if>">
					<label for="introduced">Introduced date</label>
					<div class="input">
						<form:input path="dscountedDate" />
						<c:if test="${!empty result.getFieldError('dscountedDate')}"><div>La date doit être en "yyyy-MM-dd"</div></c:if>
					</div>
				</div>


				<div class="clearfix ">
					<label for="company">Company</label>
					<div class="input">

						<select id="company" name="company">
							<c:if
								test="${computer.company==null or computer.company==0}">
								<option class="blank" value="">-- Choose a company --</option>
							</c:if>
							<c:forEach var="comp" items="${company}">
								<c:choose>
									<c:when test="${comp.idCompany==computer.company}">
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
				or <a href="/Play2v2/Computers.html" class="btn">Cancel</a>
			</div>


		</form:form>




		<form action="/Play2v2/Delete.html?id=${computer.idComputer}"
			method="POST" class="topRight">
			<input type="submit" value="Delete this computer" class="btn danger">
		</form>



	</section>

</body>
</html>

