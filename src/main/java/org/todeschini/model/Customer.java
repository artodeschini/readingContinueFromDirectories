package org.todeschini.model;

/**
 * Created by Artur on 28/05/18.
 */
public class Customer {

    //002çCNPJçNameçBusinessArea
    private String cnpj;
    private String name;
    private String businessArea;

    public Customer(String cnpj, String name, String businessArea) {
        this.setCnpj(cnpj);
        this.setName( name);
        this.setBusinessArea( businessArea );
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if ( cnpj == null ) {
            cnpj = "";
        }
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ( name == null ) {
            name = "";
        }
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        if ( businessArea == null) {
            businessArea = "";
        }
        this.businessArea = businessArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return cnpj.equals(customer.cnpj);

    }

    @Override
    public int hashCode() {
        return cnpj.hashCode();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", businessArea='" + businessArea + '\'' +
                '}';
    }
}
