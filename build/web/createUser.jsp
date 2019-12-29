<%-- 
    Document   : createUser
    Created on : Dec 1, 2019, 5:12:40 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        
        <h1>Create A New User</h1>
        <form method="POST" action="createUser">
            Email: <input type="text" name="email" value="<s:property value="email"/>" />
            <s:if test="%{!error.email.isEmpty()}">
                <font color="red">
                <s:property value="error.email"/>
                </font>
            </s:if>
            <br>
            Password: <input type="password" name="password" value="<s:property value="password"/>" />
            <s:if test="%{!error.password.isEmpty()}">
                <font color="red">
                <s:property value="error.password"/>
                </font>
            </s:if>
            <br>
            Password: <input type="password" name="conPassword" value="<s:property value="conPassword"/>" />
            <s:if test="%{!error.conPassword.isEmpty()}">
                <font color="red">
                <s:property value="error.conPassword"/>
                </font>
            </s:if>
            <br>
            Full Name: <input type="text" name="fullName" value="<s:property value="fullName"/>" />
            <s:if test="%{!error.fullName.isEmpty()}">
                <font color="red">
                <s:property value="error.fullName"/>
                </font>
            </s:if>
            <br>
            Phone: <input type="text" name="phone" value="<s:property value="phone"/>" />
            <s:if test="%{!error.phone.isEmpty()}">
                <font color="red">
                <s:property value="error.phone"/>
                </font>
            </s:if>
            <br>
            Address: <input type="text" name="address" value="<s:property value="address"/>" />
            <s:if test="%{!error.address.isEmpty()}">
                <font color="red">
                <s:property value="error.address"/>
                </font>
            </s:if>
            <br>
            <input type="submit" value="Create" />
            <input type="reset" value="Reset" />
        </form>
            
    </body>
</html>
