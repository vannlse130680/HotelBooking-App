/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import cart.CartObj;
import cart.Room;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Acer
 */
public class UpdateItemAction {

    private String roomId;
    private String quantity;
    private String updateError;
    private static final String SUCCESS = "success";

    public UpdateItemAction() {
    }

    public String execute() throws Exception {
        
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {

            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                try {

                    int quantityNum = Integer.parseInt(quantity);
                    if (quantityNum == 0) {
                        cart.removeItemFromCart(roomId);
                    }
                    if (quantityNum < 0) {
                        throw new Exception();
                    }
                    Map<String, Room> item = cart.getItems();
                    if (item != null) {
                        if (item.containsKey(roomId)) {
                            Room r = item.get(roomId);
                            r.setQuantity(quantityNum);
                        }
                    }

                    session.setAttribute("CART", cart);
                } catch (Exception e) {
                    updateError = "Invalid value of amount !!";
                }

            }
        }
        return SUCCESS;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUpdateError() {
        return updateError;
    }

    public void setUpdateError(String updateError) {
        this.updateError = updateError;
    }
    
}
