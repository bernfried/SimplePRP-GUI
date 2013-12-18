<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row" ng-controller="UserCtrl"
	ng-init="init('<%=request.getSession().getAttribute("principal")%>')">

	<h2 style="border-bottom: 2px solid #000; margin-bottom: 15px;">
		<span class="glyphicon glyphicon-user">&nbsp;</span>
		<spring:message code="profile.headline" />
	</h2>

	<spring:url var="profileUpdateUrl"
		value="/internal/user/profile/{{user.id}}"></spring:url>
	<form:form action="${profileUpdateUrl}" method="post"
		class="form-horizontal">

		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"><spring:message
					code="profile.id" /></label>
			<div class="col-sm-6">
				<p class="form-control-static">{{user.id}}</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="username"><spring:message
					code="profile.username" /></label>
			<div class="col-sm-6">
				<input class="form-control" id="username" name="username"
					value="{{user.username}}" disabled />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="firstName"><spring:message
					code="profile.firstName" /></label>
			<div class="col-sm-6">
				<input class="form-control" id="firstName" name="firstName"
					value="{{user.firstName}}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="lastName"><spring:message
					code="profile.lastName" /></label>
			<div class="col-sm-6">
				<input class="form-control" id="lastName" name="lastName"
					value="{{user.lastName}}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="email"><spring:message
					code="profile.email" /></label>
			<div class="col-sm-6">
				<input type="email" class="form-control" id="email" name="email"
					value="{{user.email}}" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="changedAtBy"><spring:message
					code="profile.changedAtBy" /></label>
			<div class="col-sm-6">
				<p class="form-control-static">{{user.changedAt | date:'<spring:message code="general.dateFormat.long" />'}} /
					{{user.changedBy}}</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="createdAtBy"><spring:message
					code="profile.createdAtBy" /></label>
			<div class="col-sm-6">
				<p class="form-control-static">{{user.createdAt | date:'<spring:message code="general.dateFormat.long" />'}} /
					{{user.createdBy}}</p>
			</div>
		</div>

		<div style="border-top: 2px solid #000; margin-top:15px; padding-top:15px;">
			<button type="submit" class="btn btn-success">
				<spring:message code="profile.button.save"></spring:message>
			</button>
			<button type="button" class="btn btn-primary">
				<spring:message code="profile.button.cancel"></spring:message>
			</button>
		</div>
	</form:form>
</div>