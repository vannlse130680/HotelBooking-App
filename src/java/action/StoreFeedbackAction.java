/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.tbl_bookdetail.Tbl_BookDetail;

/**
 *
 * @author Acer
 */
public class StoreFeedbackAction {

    private int bookingId;
    private String roomId;
    private String fbDes;
    private String fbId;
    private static final String SUCCESS = "success";

    public StoreFeedbackAction() {
    }

    public String execute() throws Exception {
        Tbl_BookDetail dao = new Tbl_BookDetail();
        dao.updateFeedback(bookingId, roomId, fbId, fbDes);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("BOOKINGID");
        session.removeAttribute("ROOMID");
        session.removeAttribute("LISTFB");
        return SUCCESS;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getFbDes() {
        return fbDes;
    }

    public void setFbDes(String fbDes) {
        this.fbDes = fbDes;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

}
