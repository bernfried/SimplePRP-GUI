<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<spring:url var="resourceUrl" value="/resources"></spring:url>

<link rel="shortcut icon" href="${resourceUrl}/ico/favicon.png">
<title><spring:message code="login.head.title" text="Authentication" /></title>

<!-- Bootstrap core CSS -->
<link href="${resourceUrl}/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${resourceUrl}/css/signin.css" rel="stylesheet">
<link href="${resourceUrl}/css/theme.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="${resourceUrl}/js/html5shiv.js"></script>
  <script src="${resourceUrl}/js/respond.min.js"></script>
<![endif]-->
