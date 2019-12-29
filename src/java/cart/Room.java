/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Room implements Serializable{
    String hotelName;
    String romId;
    String type;
    int quantity;
    float price;

    public Room() {
    }

    public Room(String hotelName, String romId, String type, int quantity, float price) {
        this.hotelName = hotelName;
        this.romId = romId;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRomId() {
        return romId;
    }

    public void setRomId(String romId) {
        this.romId = romId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

   public float getTotal() {
        return  quantity * price;
    }
    
    
}
