/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_feedback;

import java.io.Serializable;
import java.sql.Connection;
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
public class Tbl_FeedbackDAO implements Serializable{
    List<Tbl_FeedbackDTO> listFeedback;

    public List<Tbl_FeedbackDTO> getListFeedback() {
        return listFeedback;
    }
    public void loadListFeedback() throws SQLException, NamingException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select feedbackid, feedbackvalue from tbl_feedbacktype";
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

               String fbId = rs.getString("feedbackid");
               String fbValue = rs.getString("feedbackvalue");
                
                Tbl_FeedbackDTO dto = new Tbl_FeedbackDTO(fbId , fbValue);
                if (listFeedback == null) {
                    listFeedback = new ArrayList<>();
                }
                listFeedback.add(dto);
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

    }
}
