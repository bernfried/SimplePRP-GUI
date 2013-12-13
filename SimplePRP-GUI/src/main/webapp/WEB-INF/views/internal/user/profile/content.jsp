<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-xs-10 col-md-6">
	    <h2><spring:message code="profile.headline"/></h2>
		<spring:url var="profileUpdateUrl" value="/internal/user/profile/${user.id}"></spring:url>
  		<form:form action="${profileUpdateUrl}" method="post">
	  		<div class="form-group">
	    		<label for="username"><spring:message code="profile.username" /></label>
	    		<input class="form-control" id="username" name="username" value="${user.username}" />
	  		</div>
	  		<div class="form-group">
	    		<label for="firstName"><spring:message code="profile.firstName" /></label>
	    		<input class="form-control" id="firstName" name="firstName" value="${user.firstName}" />
	  		</div>
	  		<div class="form-group">
	    		<label for="lastName"><spring:message code="profile.lastName" /></label>
	    		<input class="form-control" id="lastName" name="lastName" value="${user.lastName}" />
	  		</div>
	  		<div class="form-group">
	    		<label for="email"><spring:message code="profile.email" /></label>
	    		<input type="email" class="form-control" id="email" name="email" value="${user.email}" />
	  		</div>
	  		<button type="submit" class="btn btn-success"><spring:message code="profile.button.save"></spring:message></button>
	  		<button type="button" class="btn btn-primary"><spring:message code="profile.button.cancel"></spring:message></button>
		</form:form>
  	</div>
</div>