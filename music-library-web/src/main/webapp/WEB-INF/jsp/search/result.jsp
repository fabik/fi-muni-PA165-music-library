<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:message key="search.title" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">
	<form class="form-inline" method="get" action="${pageContext.request.contextPath}/search/result">
			<div class="form-group form-group-lg">
				<input pattern=".{3,}"   required title="Minimum 3 char!"type="text" name="searchTerm" class="form-control"/>
			</div>
			<button type="submit" class="btn btn-primary btn-lg"><fmt:message key="welcome.search"/></button>
			<div class="help-block">Minimum 3 char</div>
	</form>
	<h2><fmt:message key="search.result"/> ${searchTerm}</h2>
	<c:if test="${fn:length(albums) gt 0}">
	<div>
		<h3>
			<fmt:message key="search.album"/>
		</h3>
		<div class="row">
		<c:forEach items="${albums}" var="album"  begin = "0" end = "3">
		<div class="col-sm-6 col-md-4">
			<div class="thumbnail">
				<img src="${pageContext.request.contextPath}/search/albumImage/${album.id}" alt="${album.name}">
				<div class="caption">
					<h3>${album.title}</h3>
					<p>${album.commentary}</p>
					<p>Released: <fmt:formatDate pattern = "dd. MM. yyyy" value = "${album.releaseDate}" />
					<p><a href="/album/view/${album.id}" class="btn btn-primary" role="button">Detail</a></p>
				</div>
			</div>
		</div>
		</c:forEach>
		</div>
	</div>
	<hr>
	</c:if>
	<c:if test="${fn:length(genres) gt 0}">
	<div>
		<h2>
			<fmt:message key="search.genre"/>
		</h2>
		<table class="table">
		<thead>
		<tr>
			<th><fmt:message key="search.genre.name"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${genres}" var="genre"  begin = "0" end = "4">
			<tr>
				<td><c:out value="${genre.name}"/></td>
				<td>
					<my:a href="/genre/view/${genre.id}" class="btn btn-primary"><fmt:message key="genre.detail"/></my:a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
	<hr>
    </c:if>	
	<c:if test="${fn:length(songs) gt 0}">
	<div>
		<h3>
			<fmt:message key="search.song"/>
		</h3>
		<table class="table">
		<thead>
		<tr>
			<th><fmt:message key="search.song.name"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${songs}" var="song"  begin = "0" end = "4">
			<tr>
				<td><c:out value="${song.title}"/></td>
				<td>
					<my:a href="/song/view/${song.id}" class="btn btn-primary"><fmt:message key="genre.detail"/></my:a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
	<hr>
	</c:if>
	<c:if test="${fn:length(musicians) gt 0}">
	<div>
		<h3>
			<fmt:message key="search.musicians"/>
		</h3>
		<table class="table">
		<thead>
		<tr>
			<th><fmt:message key="search.musician.name"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${musicians}" var="musician"  begin = "0" end = "4">
			<tr>
				<td><c:out value="${musician.name}"/></td>
				<td>
					<my:a href="/musician/view/${musician.id}" class="btn btn-primary"><fmt:message key="genre.detail"/></my:a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
	</c:if>
	<c:if test="${(fn:length(albums) lt 1)and(fn:length(songs) lt 1)and(fn:length(musicians) lt 1)and(fn:length(genres) lt 1)}">
		<h3><fmt:message key="search.noresult"/></h3>
	</c:if>
</jsp:attribute>
</my:pagetemplate>
