<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/client">
    <label>Name</label>
    <input type="text" name="name"/>

    <label>Surname</label>
    <input type="text" name="surname"/>

    <label>Login</label>
    <input type="text" name="login"/>

    <label>Password</label>
    <input type="password" name="password"/>

    <label>City</label>
    <input type="text" name="city"/>

    <label>Street</label>
    <input type="text" name="street"/>

    <label>House</label>
    <input type="text" name="house"/>

    <label>Flat</label>
    <input type="text" name="flat"/>

    <button type="submit">Save</button>
</form>
</body>
</html>
