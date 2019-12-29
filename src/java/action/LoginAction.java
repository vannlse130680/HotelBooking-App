/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.tbl_user.Tbl_UserDAO;

/**
 *
 * @author Acer
 */
public class LoginAction {
    private String email;
    private String password;
    private final String USER = "user";
    private final String ADMIN = "admin";
    private final String FAIL = "fail";
    public LoginAction() {
    }
    
    public String execute() throws Exception {
        String result = FAIL;
        Tbl_UserDAO dao = new Tbl_UserDAO();
        String fullName; 
        int role = dao.checkLogin(email, password);
        if(role == -1) {
            result = FAIL;
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();

            
            if (role == 1) {
                fullName = dao.getFullName(email);
                session.setAttribute("ADMIN", fullName);
                result = ADMIN;
            } 
            if( role == 0) {
                fullName = dao.getFullName(email);
                session.setAttribute("USER", fullName);
                session.setAttribute("EMAIL", email);
                result = USER;
            }
        }
        
        return  result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
