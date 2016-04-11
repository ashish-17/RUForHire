<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search for jobs here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.2.3/jquery.min.js"></script>
</head>
<body>
	<input id = "search-query" type="text" />
	<input id = "search-location" type="text" />
	<input type="submit" name="Search" value="Search Jobs" id="search-btn">
	<div id = "search-result"></div>
	<script>
	$("#search-btn").click(function(){
		var req = "jobsearch/" + $("#search-query").val() + "/" + $("#search-location").val() + "/" + "0";
	    $.ajax({url: req, success: function(result){
	        $("#search-result").html(result);
	    }});
	});
	</script>
</body>
</html>