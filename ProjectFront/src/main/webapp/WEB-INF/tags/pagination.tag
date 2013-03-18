<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag isELIgnored="false" %>
<%@ attribute name="numpage" required="true" type="java.lang.Integer" %>
<%@ attribute name="f" required="true" type="java.lang.String" %>
<%@ attribute name="s" required="true" type="java.lang.String" %>
<%@ attribute name="searchC" required="true" type="java.lang.String" %>
<%@ attribute name="nbel" required="true" type="java.lang.Integer" %>
	<div id="pagination" class="pagination">
		<ul>
			<c:choose>
				<c:when test="${numpage<1}">
					<li class="prev disabled"><a>&larr; Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="prev"><a
						href="/Play2v2/Computers.html?p=${numpage-1}
						<c:if test="${f != null}">
							&f=${f }
						</c:if>
						<c:if test="${searchC != null}">
							&searchC=${searchC }
						</c:if>
						<c:if test="${s != null}">&s=${s }</c:if>
					">&larr;
							Previous</a></li>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${numpage*10+10>nbel}">
					<li class="current"><a>Displaying ${numpage*10} to ${nbel}
							of ${nbel}</a></li>
				</c:when>
				<c:otherwise>
					<li class="current"><a>Displaying ${numpage*10} to
							${numpage*10+10} of ${nbel}</a></li>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${(numpage+1)*10>nbel}">
					<li class="prev disabled"><a>Next &rarr;</a></li>
				</c:when>
				<c:otherwise>
					<li class="next"><a
						href="/Play2v2/Computers.html?p=${numpage+1}
						<c:if test="${f != null}">&f=${f }</c:if>
						<c:if test="${s != null}">&s=${s }</c:if>	
						<c:if test="${searchC != null}">&searchC=${searchC }</c:if>					
						">
							Next &rarr;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>