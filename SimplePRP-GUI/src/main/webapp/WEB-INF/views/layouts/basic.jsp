<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en" ng-app="simplePrpApp">
	<head>
		<tiles:insertAttribute name="head"/>
	</head>
	<body>
   	  	<tiles:insertAttribute name="header"/>
    	<div class="container theme-showcase">
		  	<tiles:insertAttribute name="content"/>
	    </div>
	  	<tiles:insertAttribute name="footer"/>
	  	<tiles:insertAttribute name="foot"/>
	</body>
</html>