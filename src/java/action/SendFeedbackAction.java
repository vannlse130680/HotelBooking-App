/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.tbl_bookdetail.Tbl_BookDetail;
import sample.tbl_feedback.Tbl_FeedbackDAO;
import sample.tbl_feedback.Tbl_FeedbackDTO;

/**
 *
 * @author Acer
 */
public class SendFeedbackAction {

    private int bookingId;
    private String roomId;
    private String hotelNameSearch;
    private String bookingDateSearch;
    private String isFeedbacked;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public SendFeedbackAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        Tbl_BookDetail bDao = new Tbl_BookDetail();
       
        if (!bDao.isFeedbacked(bookingId, roomId)) {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            
            Tbl_FeedbackDAO fDao = new Tbl_FeedbackDAO();
            fDao.loadListFeedback();
            List<Tbl_FeedbackDTO> list = fDao.getListFeedback();
            session.setAttribute("BOOKINGID", bookingId);
            session.setAttribute("ROOMID", roomId);
            session.setAttribute("LISTFB", list);
            url = SUCCESS;
        } else {
            
            isFeedbacked = "You have already feedbacked for Room ID " + roomId;
        }
        return url;
    }

    public String getIsFeedbacked() {
        return isFeedbacked;
    }

    public void setIsFeedbacked(String isFeedbacked) {
        this.isFeedbacked = isFeedbacked;
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

    public String getHotelNameSearch() {
        return hotelNameSearch;
    }

    public void setHotelNameSearch(String hotelNameSearch) {
        this.hotelNameSearch = hotelNameSearch;
    }

    public String getBookingDateSearch() {
        return bookingDateSearch;
    }

    public void setBookingDateSearch(String bookingDateSearch) {
        this.bookingDateSearch = bookingDateSearch;
    }

}
