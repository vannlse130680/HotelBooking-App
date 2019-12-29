/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import sample.tbl_booking.Tbl_BookingDAO;

/**
 *
 * @author Acer
 */
public class DeleteAction {
    private int bookingId;
    private String hotelNameSearch;
    private String bookingDateSearch;
    private static final String SUCCESS = "success";
    public DeleteAction() {
    }
    
    public String execute() throws Exception {
        Tbl_BookingDAO dao = new Tbl_BookingDAO();
        dao.deleteBooking(bookingId);
        return SUCCESS;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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
