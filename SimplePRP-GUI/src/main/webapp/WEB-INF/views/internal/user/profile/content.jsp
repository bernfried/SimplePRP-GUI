<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row" ng-controller="UserCtrl"
	ng-init="init('<%=request.getSession().getAttribute("principal")%>')">

	<h2 style="border-bottom: 2px solid #000; margin-bottom: 15px;">
		<span class="glyphicon glyphicon-user">&nbsp;</span>
		<spring:message code="profile.headline" />
	</h2>

  	<div ng-if="retCode != '0' && retCode != ''" class="alert alert-danger">{{ 'USER_UPDATE_RESPONSE_CODE_' + retCode | translate }}</div>
  	<div ng-if="retCode == '0'" class="alert alert-success">{{ 'USER_UPDATE_RESPONSE_CODE_0' | translate }}</div>

	<form class="form-horizontal">

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
				<input class="form-control" id="username" ng-model="user.username" disabled />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="firstName"><spring:message
					code="profile.firstName" /></label>
			<div class="col-sm-6">
				<input class="form-control" id="firstName" ng-model="user.firstName" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="lastName"><spring:message
					code="profile.lastName" /></label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="lastName" ng-model="user.lastName" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="email"><spring:message
					code="profile.email" /></label>
			<div class="col-sm-6">
				<input type="email" class="form-control" id="email" ng-model="user.email" />
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
		    <spring:url var="homeUrl" value="/internal/home"></spring:url>
			<a href="${homeUrl}" class="btn btn-primary">
				<spring:message code="profile.button.cancel"></spring:message>
			</a>
			<button class="btn btn-success" ng-click="save()">
				<spring:message code="profile.button.save"></spring:message>
			</button>
		</div>
	</form>
</div>