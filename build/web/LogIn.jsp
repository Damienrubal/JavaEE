<%-- 
    Document   : LogIn
    Created on : 25 nov. 2016, 16:58:28
    Author     : Dams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Log In</h1>
        <p>Enter a login and a password</p>
        <form action='Controller' method='post'>
        Login: <input type='text' name='login'/>
        Password: <input type='password' name='password'/>
        <input type='submit' value='Connect' name="button"/></form><br/>
    </body>
</html>

