/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_code;

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
public class Tbl_CodeDAO implements Serializable{
    public int getDiscountCode(String code, String date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select codePercent from tbl_code where codeid = ? and expiryDate >= ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
                pst.setString(2, date);
               
                rs = pst.executeQuery();

                if (rs.next()) {
                    return rs.getInt("codePercent");
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
}
