<%-- 
    Document   : viewHistory
    Created on : Dec 6, 2019, 4:03:29 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
    </head>
    <body onload="myFunction()">
        <font color="red">
        Welcome, <s:property value="%{#session.USER}"/> <br>

        </font>
        <a href="logout">Log out</a>
        <h1>History Booking</h1>
        <form action="searchHistory">
            Search by Hotel Name: <input type="text" name="hotelNameSearch" value="<s:property value="hotelNameSearch" />" /> 
            <input type="submit" value="Search" />
        </form>
        <form action="searchHistory">
            Search by Booking Date: <input type="text" name="bookingDateSearch" id="datepicker" value="<s:property value="bookingDateSearch" />">
            <input type="submit" value="Search" />
        </form>
        <s:if test="%{listHistory != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No. 1</th>
                        <th>Booking ID</th>
                        <th>Booking Date</th>
                        <th>Check In Date</th>
                        <th>Check Out Date</th>
                        <th>Hotel Name</th>
                        <th>Room ID</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Code</th>
                        <th>Total All</th>
                        <th>Delete</th>
                        <th>Feedback</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="listHistory" status="counter">
                        <tr>
                            <td>
                                <s:property value="%{#counter.count}"/>
                            </td>
                            <td>
                                <s:property value="bookId"/>
                            </td>
                            <td>
                                <s:property value="bookDate"/>
                            </td>
                            <td>
                                <s:property value="checkInDate"/>
                            </td>
                            <td>
                                <s:property value="checkOutDate"/>
                            </td>
                            <td>
                                <s:property value="hotelName"/>
                            </td>
                            <td>
                                <s:property value="roomId"/>
                            </td>
                            <td>
                                <s:property value="quantity"/>
                            </td>                           
                            <td>
                                <s:property value="total"/>
                            </td>
                            <td>
                                <s:property value="code"/>
                            </td>
                            <td>
                                <s:property value="allTotal"/>
                            </td>

                            <s:form action="delete" theme="simple">

                                <td>
                                    <!--HIDDEN DATA-->        
                                    <s:hidden name="bookingId" value="%{bookId}" />

                                    <s:hidden name="hotelNameSearch" value="%{hotelNameSearch}" />
                                    <s:hidden name="bookingDateSearch" value="%{bookingDateSearch}" />

                                    <s:submit value="Delete" />
                                </td>

                            </s:form>

                            <s:form action="moveToFeedbackPage" theme="simple">

                                <td>
                                    <!--HIDDEN DATA-->        
                                    <s:hidden name="bookingId" value="%{bookId}" />
                                    <s:hidden name="roomId" value="%{roomId}" />

                                    <s:hidden name="hotelNameSearch" value="%{hotelNameSearch}" />
                                    <s:hidden name="bookingDateSearch" value="%{bookingDateSearch}" />

                                    <s:submit value="Feedback" />
                                </td>

                            </s:form>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>

        </s:if>
        <s:else>
            <h2>
                No history is found !!!


            </h2>
        </s:else>
            <a href="moveToUserSearch">Back to booking page ....</a>
        <script>
            function myFunction() {
            <s:if test="%{!isFeedbacked.isEmpty()}">
                alert("<s:property value="isFeedbacked" />");
            </s:if>

            }

        </script>
    </body>
</html>
