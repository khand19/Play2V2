<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pag" tagdir="/WEB-INF/tags"%>
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
			<h1>No computer found</h1>
		</c:when>
		<c:otherwise>
			<h1>${nbel} computers found</h1>
		</c:otherwise>
	</c:choose> <c:choose>
		<c:when test="${delete==true}">
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

	<div id="actions">
		<form action="/Play2v2/Computers.html" method="GET">
			<input type="search" id="searchbox" name="f"
				value="<c:if test="${param.f != null}">${param.f }</c:if>"
				placeholder="Filter by computer name...">
			<c:if test="${param.s != null}">
				<input type="hidden" id="s" name="s" value="${param.s }">
			</c:if>
			<input type="submit" id="searchsubmit" value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="/Play2v2/ComputerId.html">Add
			a new computer</a>
	</div>

	<table class="computers zebra-striped">
		<thead>
			<tr>
				<th
					class="col2 header <c:if test="${param.s==1 or param.s==-1}"> headerSort<c:choose><c:when test="${param.s==-1}">Down</c:when><c:otherwise>Up</c:otherwise></c:choose></c:if>"><a
					href="/Play2v2/Computers.html?s=<c:if test="${param.s > 0}">-</c:if>1
				<c:if test="${param.f != null}">&f=${param.f }</c:if>">Computer
						name</a></th>
				<th
					class="col3 header <c:if test="${param.s==2 or param.s==-2}"> headerSort<c:choose><c:when test="${param.s==-2}">Down</c:when><c:otherwise>Up</c:otherwise></c:choose></c:if>"><a
					href="/Play2v2/Computers.html?s=<c:if test="${param.s > 0}">-</c:if>2
				<c:if test="${param.f != null}">&f=${param.f }</c:if>">Introduced</a>
				</th>
				<th
					class="col4 header <c:if test="${param.s==3 or param.s==-3}"> headerSort<c:choose><c:when test="${param.s==-3}">Down</c:when><c:otherwise>Up</c:otherwise></c:choose></c:if>"><a
					href="/Play2v2/Computers.html?s=<c:if test="${param.s > 0}">-</c:if>3
				<c:if test="${param.f != null}">&f=${param.f }</c:if>">Discontinued</a>
				</th>
				<th
					class="col5 header <c:if test="${param.s==4 or param.s==-4}"> headerSort<c:choose><c:when test="${param.s==-4}">Down</c:when><c:otherwise>Up</c:otherwise></c:choose></c:if>"><a
					href="/Play2v2/Computers.html?s=<c:if test="${param.s > 0}">-</c:if>4
					<c:if test="${param.f != null}">&f=${param.f }</c:if>">Company</a></th>
			</tr>
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
	<pag:pagination numpage="${numpage}" f="${param.f}" s="${param.s}"
		nbel="${nbel}" /> </section>
</body>
</html>