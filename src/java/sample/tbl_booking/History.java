/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_booking;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class History implements Serializable {

    int bookId;
    String checkInDate;
    String checkOutDate;
    String bookDate;
    String hotelName;
    String roomId;
    String quantity;
    float total;
    
    String code;
    float allTotal;
    

    public History() {
    }

    public History(int bookId, String checkInDate, String checkOutDate, String bookDate, String hotelName, String roomId, String quantity, float total, String code, float allTotal) {
        this.bookId = bookId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookDate = bookDate;
        this.hotelName = hotelName;
        this.roomId = roomId;
        this.quantity = quantity;
        this.total = total;
        this.code = code;
        this.allTotal = allTotal;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(float allTotal) {
        this.allTotal = allTotal;
    }

   
}
