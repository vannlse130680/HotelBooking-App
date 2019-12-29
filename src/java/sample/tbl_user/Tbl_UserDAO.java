/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_user;

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
public class Tbl_UserDAO implements Serializable{
     public int checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select isAdmin from tbl_User where email = ? and password = ? and status = 'Active'";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();

                if (rs.next()) {
                    boolean isAd = rs.getBoolean("isAdmin");
                    if (isAd) {
                        return 1;
                    } else {
                        return 0;
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
        return -1;
    }
     public String getFullName(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select fullname from tbl_user where email = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getString("fullname");
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
        return "";
    }
     public boolean isDuplicateEmail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select email from tbl_user where email = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
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
     public boolean insertUser(String email,String password,  String fullName, String address, String phone, String date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_user(email, password,fullname, address, phone, isAdmin, createDate, status) values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                pst.setString(3, fullName);
                pst.setString(4, address);
                pst.setString(5, phone);
                pst.setBoolean(6, false);
                pst.setString(7, date);
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
}
