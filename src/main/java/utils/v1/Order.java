package utils.v1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;;


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

    
    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Order[" + id + ":" + description + "]";
    }

}
