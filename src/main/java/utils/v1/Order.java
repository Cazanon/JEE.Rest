package utils.v1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Order {
    private int id;

    private String description;

    public Order() {
    }

    public Order(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Order(String description) {
        this(0, description);
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

    @Override
    public String toString() {
        return "Order[" + id + ":" + description + "]";
    }

}
