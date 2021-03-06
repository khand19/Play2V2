<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="lbl"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<section id="main"> <c:choose>
		<c:when test="${nbel==1}">
			<h1>One computer found</h1>
		</c:when>
		<c:when test="${nbel==0}">
			<h1>
				<lbl:message code='page.dashboard.title' />
			</h1>
		</c:when>
		<c:otherwise>
			<h1>
				<lbl:message code='page.dashboard.title' arguments="${nbel}" />
			</h1>
		</c:otherwise>
	</c:choose> <c:choose>
		<c:when test="${delete}">
			<div class="alert-message warning">
				<strong>Done!</strong> Computer has been deleted
			</div>
		</c:when>
		<c:when test="${update==true}">
			<div class="alert-message warning">
				<strong>Done!</strong> Computer <strong>${name}</strong> has been
				updated
			</div>
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	<div style="position: absolute; top: 65px; right: 28px;">
		Ln: <a href="?ln=fr_FR">FR</a> / <a href="?ln=en_EN">EN</a>
	</div>
	<div id="actions">
		<form action="/Play2v2/Computers.html" method="GET">
			<input type="search" id="searchbox" name="f"
				value="<c:if test="${param.f != null}">${param.f }</c:if>"
				placeholder="<lbl:message code='form.search'/>">
			<c:if test="${param.s != null}">
				<input type="hidden" id="s" name="s" value="${param.s }">
			</c:if>
			<input type="search" id="searchC" name="searchC"
				value="<c:if test="${param.searchC != null}">${param.searchC }</c:if>"
				placeholder="<lbl:message code='form.search.company'/>"> <input
				type="submit" id="searchsubmit"
				value="<lbl:message code='form.submit.filterByName'/>"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="/Play2v2/ComputerId.html"><lbl:message
				code='page.dashboard.addNewComputer' /></a>

	</div>
	<div style="position: absolute; top: 85px; right: 28px;">
		<a class="btn success" id="add"
			href='<c:url value="j_spring_security_logout"/>'>Déconnexion</a>
	</div>

	<table class="computers zebra-striped">
		<thead>
			<tr>
				<pag:collone f="${param.f}" s="${param.s}" searchC="${param.searchC}" name="Computer name" col="1"/>
				<pag:collone f="${param.f}" s="${param.s}" searchC="${param.searchC}" name="Introduced" col="2"/>
				<pag:collone f="${param.f}" s="${param.s}" searchC="${param.searchC}" name="Discontinued" col="3"/>
				<pag:collone f="${param.f}" s="${param.s}" searchC="${param.searchC}" name="Company" col="4"/>
		</thead>
		<tbody>

			<c:forEach var="comp" items="${computer}">
				<tr>
					<td><a href="/Play2v2/ComputerId.html?id=${comp.idComputer}">${comp.nameComputer}</a></td>
					<td><em><joda:format value="${comp.introducedDate}"
								style="M-" /></em></td>
					<td><em><joda:format value="${comp.dscountedDate}"
								style="M-" /></em></td>
					<td>${comp.company.nameCompany}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<pag:pagination numpage="${numpage}" f="${param.f}" s="${param.s}" searchC="${param.searchC}"
		nbel="${nbel}" /> </section>
</body>
</html>