/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_booking;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_BookingDAO implements Serializable {
     List<History> listHistory;
    public List<History> getListHistory() {
        return  listHistory;
    }
    public int getRowTable() throws NamingException, SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int row = 0;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select BookingID from tbl_booking";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    row = rs.getRow();
                }
                return row;

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return row;

    }

    public boolean insertBooking(int bookingId, String email, String checkInDate, String checkOutDate, String bookingDate, String code, float total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_booking(bookingId, email,checkInDate,checkOutDate, bookingDate,codeid,total, status ) values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, bookingId);
                pst.setString(2, email);
                pst.setString(3, checkInDate);
                pst.setString(4, checkOutDate);
                pst.setString(5, bookingDate);
                pst.setString(6, code);
                pst.setFloat(7, total);
                pst.setString(8, "Active");

                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }

        } finally {

            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void loadHistory(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select b.BookingID, BookingDate, checkInDate, checkOutDate,h.hName,   d.rid, d.rQuantity, d.total, b.codeID, b.Total as AllTotal "
                    + "from Tbl_Booking b, Tbl_BookDetail d, Tbl_Rom r , Tbl_Hotel h "
                    + "where b.BookingID = d.BookingID and r.hID = h.hID and d.rId = r.rID and email = ? and status = ? "
                    + "ORDER BY CONVERT(DATE, BookingDate) asc";
            pst = con.prepareCall(sql);
            pst.setString(1, email);
            pst.setString(2, "Active");
            rs = pst.executeQuery();

            while (rs.next()) {

                int bookingId = rs.getInt("bookingid");
                String date = rs.getString("BookingDate");
                String checkInDate = rs.getString("checkInDate");
                String checkOutDate = rs.getString("checkOutDate");
                String hotelName = rs.getString("hName");
                String roomId = rs.getString("rid");
                String quantity = rs.getString("rQuantity");
                float total = rs.getFloat("total");
                String code = rs.getString("codeID");
                float allTotal = rs.getFloat("AllTotal");
               

                History h = new History(bookingId, checkInDate, checkOutDate, date, hotelName, roomId, quantity, total, code, allTotal);
                if (listHistory == null) {
                    listHistory = new ArrayList<>();
                }
                listHistory.add(h);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void SearchHistoryByName(String email, String hotelNameSearch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select b.BookingID, BookingDate, checkInDate, checkOutDate,h.hName,   d.rid, d.rQuantity, d.total, b.codeID, b.Total as AllTotal "
                    + "from Tbl_Booking b, Tbl_BookDetail d, Tbl_Rom r , Tbl_Hotel h "
                    + "where b.BookingID = d.BookingID and r.hID = h.hID and d.rId = r.rID and email = ? and h.hname like ? and status = ? "
                    + "ORDER BY CONVERT(DATE, BookingDate) asc";
            pst = con.prepareCall(sql);
            pst.setString(1, email);
            pst.setString(2, "%" + hotelNameSearch + "%");
            pst.setString(3, "Active");
            rs = pst.executeQuery();

            while (rs.next()) {

                int bookingId = rs.getInt("bookingid");
                String date = rs.getString("BookingDate");
                String checkInDate = rs.getString("checkInDate");
                String checkOutDate = rs.getString("checkOutDate");
                String hotelName = rs.getString("hName");
                String roomId = rs.getString("rid");
                String quantity = rs.getString("rQuantity");
                float total = rs.getFloat("total");
                String code = rs.getString("codeID");
                float allTotal = rs.getFloat("AllTotal");
               

                History h = new History(bookingId, checkInDate, checkOutDate, date, hotelName, roomId, quantity, total, code, allTotal);
                if (listHistory == null) {
                    listHistory = new ArrayList<>();
                }
                listHistory.add(h);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public void SearchHistoryByDate(String email, String bookingDateSearch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select b.BookingID, BookingDate, checkInDate, checkOutDate,h.hName,   d.rid, d.rQuantity, d.total, b.codeID, b.Total as AllTotal "
                    + "from Tbl_Booking b, Tbl_BookDetail d, Tbl_Rom r , Tbl_Hotel h "
                    + "where b.BookingID = d.BookingID and r.hID = h.hID and d.rId = r.rID and email = ? and BookingDate = ? and status = ? "
                    + "ORDER BY CONVERT(DATE, BookingDate) asc";
            pst = con.prepareCall(sql);
            pst.setString(1, email);
            pst.setString(2, bookingDateSearch);
            pst.setString(3, "Active");
            rs = pst.executeQuery();

            while (rs.next()) {

                int bookingId = rs.getInt("bookingid");
                String date = rs.getString("BookingDate");
                String checkInDate = rs.getString("checkInDate");
                String checkOutDate = rs.getString("checkOutDate");
                String hotelName = rs.getString("hName");
                String roomId = rs.getString("rid");
                String quantity = rs.getString("rQuantity");
                float total = rs.getFloat("total");
                String code = rs.getString("codeID");
                float allTotal = rs.getFloat("AllTotal");
               

                History h = new History(bookingId, checkInDate, checkOutDate, date, hotelName, roomId, quantity, total, code, allTotal);
                if (listHistory == null) {
                    listHistory = new ArrayList<>();
                }
                listHistory.add(h);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public boolean deleteBooking(int bookingId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "update tbl_booking set status = ? where bookingId = ?";
                pst = con.prepareStatement(sql);
                
                pst.setString(1, "Inactive");
                pst.setInt(2, bookingId);
                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
}

