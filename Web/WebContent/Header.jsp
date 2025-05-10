<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bean.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
</head>
<body>

	<%
		UserBean user = (UserBean) session.getAttribute("user"); /* to get user name in header  */
	%>
	<%
		if (user != null) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%>
	</h3>
	<a href="UserListCtl.do"><b>User List</b></a>
	<a href="LoginCtl?operation=logout"><b>Logout</b></a>
	<a href="UserRegistrationCtl"><b>Add User</b></a>
	<%
		} else {
	%>
	<h3>Hi, Guest</h3>
	<a href="WelcomeCtl"><b>Welcome</b></a> |
	<a href="LoginCtl"><b>Login</b></a>
	<%
		}
	%>
	<hr>
</body>
</html>