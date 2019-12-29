/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_bookdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_BookDetail implements Serializable {

    public boolean insertBookDetail(int bookingId, String roomId, int quantity, float total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_bookdetail(bookingid, rid,rquantity, total) values(?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, bookingId);
                pst.setString(2, roomId);
                pst.setInt(3, quantity);
                pst.setFloat(4, total);

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
    public boolean updateFeedback(int bookingId, String roomId, String fbId, String fbDes) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "update tbl_bookdetail set fbid = ?, fbdes = ? where bookingId = ? and rid = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, fbId);
                pst.setString(2, fbDes);
                pst.setInt(3, bookingId);
                pst.setString(4, roomId);
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
    public boolean isFeedbacked(int bookingId, String roomId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select fbid from tbl_bookdetail where bookingid = ? and rid = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, bookingId);
                pst.setString(2, roomId);
                rs = pst.executeQuery();

                if (rs.next()) {
                    if(rs.getString("fbid") != null) {
                        return true;
                    }
                }
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
        return false;
    }
}
