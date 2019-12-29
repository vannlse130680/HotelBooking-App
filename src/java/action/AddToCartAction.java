/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import cart.CartObj;
import cart.Room;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Acer
 */
public class AddToCartAction {

    private String romId;
    private String hotelName;
    private String typeName;
    private int quantity;
    private float price;
    private String hotelNameSearch;
    private String hotelAreaSearch;
    private String checkInDate;
    private String checkOutDate;
    private int amount;
    private static final String SUCCESS = "success";
    private static final String SEARCH_BY_NAME = "name";
    private static final String SEARCH_BY_AREA = "area";

    public AddToCartAction() {
    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        CartObj cart = (CartObj) session.getAttribute("CART");
        if (cart == null) {
            cart = new CartObj();
        }
        //3. lay hang
        Room r = new Room(hotelName, romId, typeName, 1, price);

        //4. bo vo gio
        cart.addItemToCart(r);
        session.setAttribute("CHECKIN", checkInDate);
        session.setAttribute("CHECKOUT", checkOutDate);
        Calendar in = new GregorianCalendar();
        Calendar out = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date dateIn = sdf.parse(checkInDate);
        in.setTime(dateIn);
        Date dateOut = sdf.parse(checkOutDate);
        out.setTime(dateOut);
        int between = daysBetween(in.getTime(), out.getTime());
        session.setAttribute("BETWEEN", between);

        session.setAttribute("CART", cart);
        if (!hotelNameSearch.isEmpty()) {
            result = SEARCH_BY_NAME;
        }
        if (!hotelAreaSearch.isEmpty()) {
            result = SEARCH_BY_AREA;
        }
        return result;
    }

    public String getRomId() {
        return romId;
    }

    public void setRomId(String romId) {
        this.romId = romId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getHotelNameSearch() {
        return hotelNameSearch;
    }

    public void setHotelNameSearch(String hotelNameSearch) {
        this.hotelNameSearch = hotelNameSearch;
    }

    public String getHotelAreaSearch() {
        return hotelAreaSearch;
    }

    public void setHotelAreaSearch(String hotelAreaSearch) {
        this.hotelAreaSearch = hotelAreaSearch;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
