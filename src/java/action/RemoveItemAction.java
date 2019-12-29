/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import cart.CartObj;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Acer
 */
public class RemoveItemAction {

    private String roomId;
    private static final String SUCCESS = "success";

    public RemoveItemAction() {
    }

    public String execute() throws Exception {
        
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {

            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {

                cart.removeItemFromCart(roomId);

                session.setAttribute("CART", cart);

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
    
}
