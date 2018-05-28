package org.todeschini.model;

/**
 * Created by Artur on 28/05/18.
 */
public class Item {

    //ItemID-ItemQuantity-ItemPrice]
    private String id;
    private Double quantity;
    private Double price;

    public Item(String id, String quantity, String price) {
        this.setId( id );
        this.setQuantity( quantity);
        this.setPrice( price );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setQuantity(String quantity) {
        try {
            this.quantity = Double.valueOf( quantity );
        } catch (Exception e) {
            this.quantity = 0.0;
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        try {
            this.price = Double.valueOf( price );
        } catch (Exception e) {
            this.price = 0.0;
        }

    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", euantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
