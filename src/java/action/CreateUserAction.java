/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import error.CreateUserError;
import sample.tbl_user.Tbl_UserDAO;

/**
 *
 * @author Acer
 */
public class CreateUserAction {

    private String email;
    private String password;
    private String conPassword;
    private String fullName;
    private String phone;
    private String address;
    private CreateUserError error;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CreateUserAction() {

    }

    public String execute() throws Exception {
        Tbl_UserDAO dao = new Tbl_UserDAO();
        String result = FAIL;
        String emailFomat = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String phoneFormat = "\\d{10,15}";
        boolean foundError = false;
        error = new CreateUserError();
        if (email.trim().isEmpty()) {
            foundError = true;
            error.setEmail("Please enter email !");
        } else {
            if (!email.matches(emailFomat)) {
                foundError = true;
                error.setEmail("Invalid Email !");
            } else {
                if (dao.isDuplicateEmail(email)) {
                    foundError = true;
                    error.setEmail("Email is duplicated !");
                }
            }
        }
        if (password.trim().isEmpty()) {
            foundError = true;
            error.setPassword("Please enter password !");
        } else if (!password.equals(conPassword)) {
            foundError = true;
            error.setConPassword("Cofirm must match password !");
        }
        if (fullName.trim().isEmpty()) {
            foundError = true;
            error.setFullName("Please enter full name!");
        }
        if (!phone.trim().matches(phoneFormat)) {
            foundError = true;
            error.setPhone("Incorrect phone number format (10-15)");
        }
        if (address.trim().isEmpty()) {
            foundError = true;
            error.setAddress("Please enter full address!");
        }

        if (!foundError) {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            result = SUCCESS;
            dao.insertUser(email, password, fullName, address, phone, date.toString());
        }
        return result;
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

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CreateUserError getError() {
        return error;
    }

    public void setError(CreateUserError error) {
        this.error = error;
    }

      
}
