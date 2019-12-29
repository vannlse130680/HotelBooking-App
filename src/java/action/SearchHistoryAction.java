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
import sample.tbl_booking.History;
import sample.tbl_booking.Tbl_BookingDAO;

/**
 *
 * @author Acer
 */
public class SearchHistoryAction {

    private String hotelNameSearch;
    private String bookingDateSearch;
    private List<History> listHistory;
    private String isFeedbacked;
    private static final String SUCCESS = "success";

    public SearchHistoryAction() {

    }

    public String execute() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        if (hotelNameSearch != null) {
            Tbl_BookingDAO dao = new Tbl_BookingDAO();
            dao.SearchHistoryByName(email, hotelNameSearch);
            listHistory = dao.getListHistory();
        }
        if (bookingDateSearch != null) {
            if (!bookingDateSearch.trim().isEmpty()) {
                Tbl_BookingDAO dao = new Tbl_BookingDAO();
                dao.SearchHistoryByDate(email, bookingDateSearch);
                listHistory = dao.getListHistory();
            } else {
                Tbl_BookingDAO dao = new Tbl_BookingDAO();
                dao.loadHistory(email);
                listHistory = dao.getListHistory();
            }

        }
        if (hotelNameSearch != null && bookingDateSearch != null) {
            if (!hotelNameSearch.trim().isEmpty()) {
                Tbl_BookingDAO dao = new Tbl_BookingDAO();
                dao.SearchHistoryByName(email, hotelNameSearch);
                listHistory = dao.getListHistory();
            } else {
                if (!bookingDateSearch.trim().isEmpty()) {
                    Tbl_BookingDAO dao = new Tbl_BookingDAO();
                    dao.SearchHistoryByDate(email, bookingDateSearch);
                    listHistory = dao.getListHistory();
                } else {
                    Tbl_BookingDAO dao = new Tbl_BookingDAO();
                    dao.loadHistory(email);
                    listHistory = dao.getListHistory();
                }
            }

        }
        return SUCCESS;
    }

    public String getIsFeedbacked() {
        return isFeedbacked;
    }

    public void setIsFeedbacked(String isFeedbacked) {
        this.isFeedbacked = isFeedbacked;
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

    public List<History> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<History> listHistory) {
        this.listHistory = listHistory;
    }

}
