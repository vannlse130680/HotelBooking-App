/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import cart.CartObj;
import cart.Room;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.tbl_bookdetail.Tbl_BookDetail;
import sample.tbl_booking.Tbl_BookingDAO;
import sample.tbl_rom.Tbl_RomDAO;

/**
 *
 * @author Acer
 */
public class ConfirmAction {

    private float totalAll;
    private String outOfStock;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public ConfirmAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        Tbl_BookingDAO bookingDao = new Tbl_BookingDAO();

        if (session != null) {
            String email = (String) session.getAttribute("EMAIL");
            String code = (String) session.getAttribute("CODE");
            String checkInDate = (String) session.getAttribute("CHECKIN");
            String checkOutDate = (String) session.getAttribute("CHECKOUT");
            int between = (int) session.getAttribute("BETWEEN");
            

            int row = bookingDao.getRowTable();
            int bookindId = ++row;

            CartObj cart = (CartObj) session.getAttribute("CART");

            if (cart != null) {

                Map<String, Room> items = cart.getItems();
                if (items != null) {
                    outOfStock = "";
                    for (Map.Entry item : items.entrySet()) {

                        Tbl_RomDAO roomDao = new Tbl_RomDAO();
                        int quantityAvail = roomDao.getRoomQuantityAvailable(checkInDate, checkOutDate, (String) item.getKey());
                        int amount = ((Room) (item.getValue())).getQuantity();
                        if (amount > quantityAvail) {
                            outOfStock = outOfStock + ((Room) (item.getValue())).getRomId() + " available: " + quantityAvail + "\\n";

                        }

                    }
                    if (outOfStock.isEmpty()) {
                        bookingDao.insertBooking(bookindId, email, checkInDate, checkOutDate, date.toString(), code, totalAll);
                        for (Map.Entry item : items.entrySet()) {
                            Tbl_RomDAO roomDao = new Tbl_RomDAO();
                            Tbl_BookDetail detailDao = new Tbl_BookDetail();
                            float rTotal = ((Room) (item.getValue())).getTotal() * between;
                            detailDao.insertBookDetail(bookindId, (String) item.getKey(), ((Room) (item.getValue())).getQuantity(), rTotal);

                        }

                        session.removeAttribute("CODE");
                        session.removeAttribute("PERCENT");
                        session.removeAttribute("CHECKIN");
                        session.removeAttribute("CHECKOUT");
                        session.removeAttribute("BETWEEN");
                        session.removeAttribute("CART");
                    } else {
                        
                        outOfStock = "Out of stock: \\n" + outOfStock;
                        url = FAIL;
                    }

                }

            }
        }
        
        
        return url;
    }

    public float getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(float totalAll) {
        this.totalAll = totalAll;
    }

    public String getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(String outOfStock) {
        this.outOfStock = outOfStock;
    }
    
}
