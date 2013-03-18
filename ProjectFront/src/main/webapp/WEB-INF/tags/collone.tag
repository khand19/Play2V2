<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="f" required="true" type="java.lang.String" %>
<%@ attribute name="s" required="true" type="java.lang.String" %>
<%@ attribute name="searchC" required="true" type="java.lang.String" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="col" required="true" type="java.lang.Integer" %>
<th
	class="col${col+1} header 
		<c:if test="${s==col or s==-col}"> headerSort
			<c:choose><c:when test="${s==-col}">Down</c:when><c:otherwise>Up</c:otherwise></c:choose>
		</c:if>">
		<a href="/Play2v2/Computers.html?s=
			<c:if test="${s > 0}">-</c:if>1
			<c:if test="${f != null}">&f=${f }</c:if>
			<c:if test="${searchC != null}">${param.searchC }</c:if>				
		">${name}</a>
</th>