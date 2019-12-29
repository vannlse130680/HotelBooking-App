<%-- 
    Document   : search
    Created on : Dec 1, 2019, 9:16:49 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Search Page</title>
        
        
    </head>
    <body>
        <font color="red">
        Welcome, <s:property value="%{#session.ADMIN}"/> <br>
        
        </font>
        <a href="logout">Log out</a> <br> <br>
        
        <h1>Admin Search Page</h1>
        Search By Hotel Name:
        <form action="searchByHoTelName">
            Hotel Name <input type="text" name="hotelName" value="<s:property value="hotelName" />" />           
            Check In Date <input type="text" name="checkInDate"  value="<s:property value="checkInDate" />">
            Check Out Date <input type="text" name="checkOutDate"  value="<s:property value="checkOutDate" />">
            Amount <input type="text" name="amount" value="<s:property value="amount" />" />
             <input type="submit" value="Search" />
            <s:if test="%{hotelName.trim().isEmpty() || checkInDate.trim().isEmpty() || checkOutDate.trim().isEmpty() || amount.trim().isEmpty()}">
                <font color="red">
                <s:property value="searchByHotelNameError"/>
                </font>
            </s:if>
           
        </form>
        Search By Hotel Area:
        <form action="searchByHoTelArea">
            Hotel Area <input type="text" name="hotelArea" value="<s:property value="hotelArea" />" />           
            Check In Date <input type="text" name="checkInDate"  value="<s:property value="checkInDate" />">
            Check Out Date <input type="text" name="checkOutDate"  value="<s:property value="checkOutDate" />">
            Amount <input type="text" name="amount" value="<s:property value="amount" />" />
             <input type="submit" value="Search" />
            <s:if test="%{hotelArea.trim().isEmpty() || checkInDate.trim().isEmpty() || checkOutDate.trim().isEmpty() || amount.trim().isEmpty()}">
                <font color="red">
                <s:property value="searchByHotelAreaError"/>
                </font>
            </s:if>
           
        </form>
        <s:if test="%{willSearch}">
            
            <s:if test="%{listRom != null}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No. 1</th>
                            <th>Hotel Name</th>
                            <th>Hotel Area</th>
                            <th>Rom Type</th>
                            <th>Price</th>
                            <th>Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listRom" status="counter">
                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}"/>
                                </td>
                                <td>
                                    <s:property value="hotelName"/>
                                </td>
                                <td>
                                    <s:property value="hotelArea"/>
                                </td>
                                <td>
                                    <s:property value="typeName"/>
                                </td>
                                <td>
                                    <s:property value="price"/>
                                </td>
                                <td>
                                    <s:property value="quantity"/>
                                </td>
                                
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>

            </s:if>
            <s:else>
                <h2>
                    No record is matched !!!
                </h2>
            </s:else>
        </s:if>
    </body>
</html>
