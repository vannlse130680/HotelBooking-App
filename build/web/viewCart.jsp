<%-- 
    Document   : viewCart
    Created on : Dec 3, 2019, 6:59:26 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body onload="myFunction()" >
        <font color="red">
        Welcome, <s:property value="%{#session.USER}"/> <br>

        </font>
        <a href="logout">Log out</a>
        <h1>Your Cart</h1>

        <s:if test="%{#session.CART == null || #session.CART.items == null}">
            <h3>No item in your cart</h3>
        </s:if>
        <s:else>
            Check in date: <s:property value="%{#session.CHECKIN}"/> <br>
            Check out date: <s:property value="%{#session.CHECKOUT}"/> 
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Hotel Name</th>
                        <th>Room ID</th>
                        <th>Room Type</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Update</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <s:set var="cartTotal" value="0"/>
                    <s:iterator value="%{#session.CART.items}" status="counter">
                        <tr>
                    <form action="updateItem">
                        <td>
                            <s:property value="#counter.count" />
                        </td>
                        <td>
                            <s:property value="value.hotelName" />
                        </td>
                        <td>
                            <s:property value="key" />
                        </td>
                        <td>
                            <s:property value="value.type" />
                        </td>

                        <td>
                            <input type="text" name="quantity" value="<s:property value="value.quantity" />" />
                        </td>
                        <td>
                            <s:property value="value.price" />
                        </td>
                        <td>
                            <s:property value="value.total * #session.BETWEEN" />
                        </td>

                        <s:set var="cartTotal" value="%{#cartTotal +  value.total * #session.BETWEEN}"/>
                        <td>
                            <input type="hidden" name="roomId" value="<s:property value="key" />" />
                            <input type="submit" value="Update" />
                        </td>  

                    </form>


                    <td>
                        <s:form action="removeItem">


                            <s:hidden name="roomId" value="%{key}" />
                            <s:submit value="Remove" onclick="return confirm('Are you sure you want to delete this item?');"/>


                        </s:form>
                    </td>

                </tr>
            </s:iterator>
            <tr>

                <td colspan="9">Cart Total: <s:property value="#cartTotal" /></td>

            </tr>
        </tbody>
    </table>
    <form action="useCode">
        Enter Discount Code: <input type="text" name="code" value="<s:property value="code" />" /> 
        <input type="submit" value="USE" />
        <br>

        <s:if test="%{#session.CODE == null}">
            <font color="red">
            <s:property value="error" />
            </font>
        </s:if>
        <s:else>
            Code: <s:property value="%{#session.CODE}"/> <br>
            <s:set var="cartTotal" value="%{#cartTotal - #cartTotal * #session.PERCENT / 100}"/>
            Total after discount(<s:property value="#session.PERCENT" /> %): <s:property value="#cartTotal" />
        </s:else>


    </form>

    <form action="confirm">
        <input type="hidden" name="totalAll" value="<s:property value="#cartTotal" />" />
        <input type="submit" value="Confirm" />
    </form>
        
</s:else>
            <a href="moveToUserSearch">Add more rooms to your cart ..</a>
<script>
    function myFunction() {
    <s:if test="%{!updateError.isEmpty()}">
        alert("<s:property value="updateError" />");
    </s:if>

      <s:if test="%{!outOfStock.isEmpty()}">
        alert("<s:property value="outOfStock" />");
    </s:if>  

    }

</script>
</body>
</html>
