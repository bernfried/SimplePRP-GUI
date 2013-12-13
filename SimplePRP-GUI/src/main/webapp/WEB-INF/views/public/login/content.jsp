<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="loginUrl" value="/static/j_spring_security_check"></spring:url>

<form class="form-signin" action="${loginUrl}" method="POST">
	<h2 class="form-signin-heading"><spring:message code="login.title" /></h2>
	<c:if test="${'true' eq param.error}">
        <div class="alert alert-danger">
        	<p><spring:message code="${loginLastError}"/></p>
        </div>
    </c:if>
	<c:if test="${'true' eq param.timeout}">
        <div class="alert alert-info">
        	<p><spring:message code="login.info.timeout"/></p>
        </div>
    </c:if>
	<input type="text" class="form-control" name="j_username" placeholder="<spring:message code="login.username" />" autofocus>
	<input type="password" name="j_password" class="form-control" placeholder="<spring:message code="login.password" />">
	<label class="checkbox">
	  	<input type="checkbox" name="_spring_security_remember_me" value="true"> <spring:message code="login.rememberme" />
	</label>
	<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.button" /></button>
</form>