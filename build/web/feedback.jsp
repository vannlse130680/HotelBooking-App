<%-- 
    Document   : feedback
    Created on : Dec 8, 2019, 9:55:35 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, <s:property value="%{#session.USER}"/> <br>

        </font>
        <a href="logout">Log out</a>
        <br>
        <h1>Send Feedback</h1>

        Booking ID: <s:property value="%{#session.BOOKINGID}"/> <br>
        Room ID: <s:property value="%{#session.ROOMID}"/> <br>
        Rate
        <form action="storeFeedback">
            <select name="fbId">
                <s:iterator value="#session.LISTFB" status="counter">
                    <option value="<s:property value="fbId"/>"><s:property value="fbValue"/></option>

                </s:iterator>
            </select>
            <br>
            Feedback Detail: <br>
            <textarea name="fbDes"></textarea>
            <input type="hidden" name="bookingId" value="<s:property value="%{#session.BOOKINGID}"/>" />
            <input type="hidden" name="roomId" value="<s:property value="%{#session.ROOMID}"/>" />
            <input type="submit" value="Send Feedback" />
        </form>

    </body>
</html>
