/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Acer
 */
public class CartObj implements Serializable{
    private Map<String, Room> items;

    public Map<String, Room> getItems() {
        return items;
    }

    public void addItemToCart(Room r) {
        if (this.items == null) {
            this.items = new HashMap<>();

        }
        int amount = 0;
        if (this.items.containsKey(r.romId)) {
            amount = this.items.get(r.romId).quantity + r.quantity;
            r.setQuantity(amount);
        }
        this.items.put(r.romId, r);

    }

    public void removeItemFromCart(String roomId) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(roomId)) {
            this.items.remove(roomId);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
