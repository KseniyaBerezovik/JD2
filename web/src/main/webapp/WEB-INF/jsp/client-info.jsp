<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client-info</title>
</head>
<body>
    <p>Name: ${client.name}</p>
    <p>Surname: ${client.surname}</p>
    <p>Login: ${client.login}</p>
    <p>City: ${client.address.city}</p>
    <p>Street: ${client.address.street}</p>
    <p>House: ${client.address.house}</p>
    <p>Flat: ${client.address.flat}</p>
</body>
</html>
