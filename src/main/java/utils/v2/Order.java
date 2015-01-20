package utils.v2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Order {
    private int id;

    private String description;
    
    private Customer customer;

    public Order() {
    }

    public Order(int id, String description, Customer client) {
        this.id = id;
        this.description = description;
        this.customer=client;
    }

    public Order(String description) {
        this(0, description,null);
    }

    @XmlAttribute
    // Se convierte en un atributo XML
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // @XmlElement: Por defecto, sobre todo lo público
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // @XmlTransient: cuando no queramos que se incorpore a XML

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order[" + this.id + ":" + this.description + "("+this.customer.toString()+")]";
    }

}
