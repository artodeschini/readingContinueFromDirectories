package org.todeschini.model;

/**
 * Created by Artur on 28/05/18.
 */
public class Salesman {

    //01çCPFçNameçSalary

    private String cpf;
    private String name;
    private Double salary;

    public Salesman(String cpf, String name, Double salary) {
        this.setCpf(cpf);
        this.setName(name);
        this.setSalary( salary );
    }

    public Salesman(String cpf, String name, String salary) {
        this.setCpf(cpf);
        this.setName(name);
        this.setSalary( salary );
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if ( cpf == null ) {
            this.cpf = "";
        }
        this.cpf = cpf;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        try {
            this.salary = Double.valueOf(salary);
        } catch (Exception e) {
            this.setSalary(0.0);
        }

    }

    public void setSalary(Double salary) {
        if ( salary == null ) {
            this.salary = salary;
        } else {
            this.salary = salary;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salesman salesman = (Salesman) o;

        if (cpf != null ? !cpf.equals(salesman.cpf) : salesman.cpf != null) return false;
        return name != null ? name.equals(salesman.name) : salesman.name == null;

    }

    @Override
    public int hashCode() {
        int result = cpf != null ? cpf.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
