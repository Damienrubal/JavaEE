<%-- 
    Document   : Process
    Created on : 25 nov. 2016, 16:59:00
    Author     : Dams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
    </head>
    <body>
        <h1>Process</h1>
        <p>Logged in : </p><%=session.getAttribute("Login")%>
        
        <form action='Controller' method='post'>            
            Name: <input type='text' name='name'/>
            Value: <input type='text' name='val'/>
  ID(only for "Update"): <input type='text' name='id'/>
            <input type='submit' value='Update' name='button'/>
            <input type='submit' value='Insert' name='button'/>
            <input type='submit' value='Delete' name='button'/>
            <input type='submit' value='Logout' name='button'/>
          <%--   <input type='submit' value='Continue' name='button'/> --%>
            
        </form>   
          
        
        <%--  <%
            Enumeration<String> attributes=session.getAttributeNames();
            while(attributes.hasMoreElements()){
                String attribute = attributes.nextElement();
                String value=session.getAttribute(attribute).toString();%>
                <%=attribute%> = <%=value%><br/><%
            }
%> --%>
                <h1>Données actuelles bdd_java.userdata</h1>
     <table border="1">        
        <c:forEach items="${sqlData}" var="message" varStatus="boucle">
            
            <tr>
            <td>${ boucle.count }. ${ message }</td>
            </tr>
       
        </c:forEach>
            
     </table>
    </body>
</html>
