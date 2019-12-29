/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.tbl_rom.Tbl_RomDAO;
import sample.tbl_rom.Tbl_RomDTO;

/**
 *
 * @author Acer
 */
public class SearchByHotelNameAction {

    private String hotelNameSearch;
    private String checkInDate;
    private String checkOutDate;
    private String amount;
    private List<Tbl_RomDTO> listRom;
    private static final String SUCCESS = "success";
    private static final String USER = "user";
    private static final String ADMIN = "admin";
    private boolean willSearch;
    private String searchByHotelNameError;

    public SearchByHotelNameAction() {
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int amountNum = 0;
        sdf.setLenient(false);
        boolean foundError = false;
        try {
            Date dateIn = (Date) sdf.parse(checkInDate);
            Date dateOut = (Date) sdf.parse(checkOutDate);
            if (dateIn.compareTo(dateOut) >= 0) {
                
                foundError = true;
            }
            if (dateIn.compareTo(sdf.parse(date.toString())) <= -1) {
                
                foundError = true;
            }
        } catch (Exception e) {
            foundError = true;

        }
        try {
            amountNum = Integer.parseInt(amount);
            if (amountNum <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            foundError = true;
        }

        if (hotelNameSearch.trim().isEmpty() || checkInDate.trim().isEmpty() || checkOutDate.trim().isEmpty() || amount.trim().isEmpty()) {
            foundError = true;
            searchByHotelNameError = "Please enter full infor to search!";
        } else {
            willSearch = true;
        }
        if (!foundError) {

            Tbl_RomDAO dao = new Tbl_RomDAO();
            
            dao.searchByHotelName(hotelNameSearch, checkInDate, checkOutDate, amountNum);
            listRom = dao.getListRom();
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("USER");
        String admin = (String) session.getAttribute("ADMIN");
        if (user != null) {
            result = USER;
        }
        if (admin != null) {
            result = ADMIN;
        }
        return result;
    }

    public String getHotelNameSearch() {
        return hotelNameSearch;
    }

    public void setHotelNameSearch(String hotelNameSearch) {
        this.hotelNameSearch = hotelNameSearch;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<Tbl_RomDTO> getListRom() {
        return listRom;
    }

    public void setListRom(List<Tbl_RomDTO> listRom) {
        this.listRom = listRom;
    }

    public String getSearchByHotelNameError() {
        return searchByHotelNameError;
    }

    public void setSearchByHotelNameError(String searchByHotelNameError) {
        this.searchByHotelNameError = searchByHotelNameError;
    }

    public boolean isWillSearch() {
        return willSearch;
    }

    public void setWillSearch(boolean willSearch) {
        this.willSearch = willSearch;
    }

}
