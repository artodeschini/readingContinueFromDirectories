package org.todeschini.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 28/05/18.
 */
public class Sale {

    //003çSaleIDç[ItemID-ItemQuantity-ItemPrice]çSalesmanname
    private String id;
    private String salesmanName;
    private Double sum = 0.0;
    private List<Item> items = new LinkedList<Item>();


    public Sale(String id, String salesmanName ) {
        this.setId( id );
        this.setSalesmanName( salesmanName );
    }

    public Sale(String id, String salesmanName, /* item details */ String idItem, String quantity, String price) {
        this(id, salesmanName);
        this.addItem( new Item( idItem, quantity, price ) );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public void addItem(Item item) {
        items.add( item );
        sum += item.getPrice() * item.getQuantity();
    }

    public void clearItens() {
        items = new LinkedList<Item>();
        sum = 0.0;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        return id != null ? id.equals(sale.id) : sale.id == null;
    }

    public Double getSum() {
        return sum;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sbItens = new StringBuilder("[");
        for (Item i: items) {
            sbItens.append( i.toString() );
            sbItens.append(",");
        }
        sbItens.append("]");

        return "Sale{" +
                "id='" + id + '\'' +
                ", salesmanName='" + salesmanName + '\'' +
                ", items=" + sbItens.toString().replace("},]", "}]") +
                '}';
    }
}