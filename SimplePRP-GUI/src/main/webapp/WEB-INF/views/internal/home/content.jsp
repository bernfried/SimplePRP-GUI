<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${'true' eq param.accessDenied}">
	<div class="alert alert-danger">
		<p><spring:message code="error.accessDenied"/></p>
	</div>
</c:if>

<c:if test="${'true' eq param.pageNotFound}">
	<div class="alert alert-danger">
		<p><spring:message code="error.pageNotFound"/></p>
	</div>
</c:if>