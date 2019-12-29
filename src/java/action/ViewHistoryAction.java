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
public class ViewHistoryAction {

    private List<History> listHistory;
    private static final String SUCCESS = "success";

    public ViewHistoryAction() {
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        Tbl_BookingDAO dao = new Tbl_BookingDAO();
        dao.loadHistory(email);
        listHistory = dao.getListHistory();
        return SUCCESS;
    }

    public List<History> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<History> listHistory) {
        this.listHistory = listHistory;
    }
    

}
