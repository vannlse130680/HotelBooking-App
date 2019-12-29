/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.tbl_code.Tbl_CodeDAO;

/**
 *
 * @author Acer
 */
public class UseCodeAction {

    private String code;
    private String error;
    private final String SUCCESS = "success";

    public UseCodeAction() {
    }

    public String execute() throws Exception {
         HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
        Tbl_CodeDAO dao = new Tbl_CodeDAO();
        
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
         int percent = dao.getDiscountCode(code, date.toString());
         
        if (percent > 0) {
           
            session.setAttribute("CODE", code);
            session.setAttribute("PERCENT", percent);
        } else {
            error = "Invalid code or this code is old !";
            session.removeAttribute("CODE");
            session.removeAttribute("PERCENT");
        }
        return SUCCESS;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

   
    
}
