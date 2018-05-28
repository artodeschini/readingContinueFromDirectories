package org.todeschini.model;

/**
 * Created by Artur on 28/05/18.
 */
public class Customer {

    //002çCNPJçNameçBusinessArea
    private String cnpj;
    private String name;
    private String businessArea;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
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
}
