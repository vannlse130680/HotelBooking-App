/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_rom;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_RomDAO implements Serializable {

    List<Tbl_RomDTO> listRom;

    public List<Tbl_RomDTO> getListRom() {
        return listRom;
    }

    public void searchByHotelName(String hotelName, String checkInDate, String checkOutDate, int quantity) throws SQLException, NamingException {
        Connection con = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "{call AvailableRoomBetweenDate(?,?,?,?,?)}";
            cst = con.prepareCall(sql);
            cst.setString(1, checkInDate);
            cst.setString(2, checkOutDate);
            cst.setInt(3, quantity);
            cst.setString(4, "%" + hotelName + "%");
            cst.setString(5, "%%");

            rs = cst.executeQuery();

            while (rs.next()) {
                String hId = rs.getString("hid");

                String hName = rs.getString("hname");
                String hArea = rs.getString("hArea");
                String rId = rs.getString("rid");
                String tName = rs.getString("tname");

                float price = rs.getFloat("rPrice");
                int rQuantity = rs.getInt("ROOM_QUANTITY_AVAILABLE");

                Tbl_RomDTO dto = new Tbl_RomDTO(hId, hName, hArea, rId, tName, rQuantity, price);

                if (listRom == null) {
                    listRom = new ArrayList<>();
                }
                listRom.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchByHotelArea(String hotelArea, String checkInDate, String checkOutDate, int quantity) throws SQLException, NamingException {
        Connection con = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "{call AvailableRoomBetweenDate(?,?,?,?,?)}";
            cst = con.prepareCall(sql);
            cst.setString(1, checkInDate);
            cst.setString(2, checkOutDate);
            cst.setInt(3, quantity);
            cst.setString(4, "%%");
            cst.setString(5, "%" + hotelArea + "%");

            rs = cst.executeQuery();

            while (rs.next()) {
                String hId = rs.getString("hid");

                String hName = rs.getString("hname");
                String hArea = rs.getString("hArea");
                String rId = rs.getString("rid");
                String tName = rs.getString("tname");

                float price = rs.getFloat("rPrice");
                int rQuantity = rs.getInt("ROOM_QUANTITY_AVAILABLE");

                Tbl_RomDTO dto = new Tbl_RomDTO(hId, hName, hArea, rId, tName, rQuantity, price);

                if (listRom == null) {
                    listRom = new ArrayList<>();
                }
                listRom.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int getRoomQuantityAvailable(String checkInDate, String checkOutDate, String roomId) throws SQLException, NamingException {
        Connection con = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "{call AvailableRoomQuantity(?,?,?)}";
            cst = con.prepareCall(sql);
            cst.setString(1, checkInDate);
            cst.setString(2, checkOutDate);
            cst.setString(3, roomId);
           

            rs = cst.executeQuery();

            if (rs.next()) {
                
                return rs.getInt("ROOM_QUANTITY_AVAILABLE");

                
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }
}
