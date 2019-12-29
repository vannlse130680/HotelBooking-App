/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_rom;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Tbl_RomDTO implements Serializable{
    String hotelId;
    String hotelName;
    String hotelArea;
    String romId;
    
    String typeName;
    int quantity;
    float price;

    public Tbl_RomDTO() {
    }

    public Tbl_RomDTO(String hotelId, String hotelName, String hotelArea, String romId, String typeName, int quantity, float price) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelArea = hotelArea;
        this.romId = romId;
        this.typeName = typeName;
        this.quantity = quantity;
        this.price = price;
    }
    
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelArea() {
        return hotelArea;
    }

    public void setHotelArea(String hotelArea) {
        this.hotelArea = hotelArea;
    }

    public String getRomId() {
        return romId;
    }

    public void setRomId(String romId) {
        this.romId = romId;
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

   
}
