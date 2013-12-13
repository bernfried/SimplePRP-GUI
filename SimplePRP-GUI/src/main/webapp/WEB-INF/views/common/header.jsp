<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<spring:url var="homeUrl" value="/internal/home"></spring:url>
<spring:url var="profileUrl" value="/internal/user/profile"></spring:url>
<spring:url var="logoutUrl" value="/static/j_spring_security_logout"></spring:url>

<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${homeUrl}"><spring:message code="application.name" /></a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <security:authorize access="hasRole('ROLE_ADMIN')">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="navigation.administration" /> <b class="caret"></b></a>
          <ul class="dropdown-menu">
          	<li><a href="#"><spring:message code="navigation.administration.users" /></a></li>
          	<li><a href="#"><spring:message code="navigation.administration.roles" /></a></li>
          </ul>
        </li>
        </security:authorize>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="navigation.language" /> <b class="caret"></b></a>
          <ul class="dropdown-menu">
          	<li><a href="?language=de_DE"><spring:message code="navigation.language.de" /></a></li>
          	<li><a href="?language=en"><spring:message code="navigation.language.en" /></a></li>
          </ul>
        </li>
        <security:authorize access="hasRole('ROLE_USER')">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=request.getSession().getAttribute("principal")%> <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="${profileUrl}"><spring:message code="navigation.profile.edit" /></a></li>
            <li class="divider"></li>
            <li><a href="${logoutUrl}"><spring:message code="navigation.profile.logout" /></a></li>
          </ul>
        </li>
        </security:authorize>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</div>