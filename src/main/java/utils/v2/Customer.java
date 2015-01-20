package utils.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
    private int id;

    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(String name) {
        this(0, name);
    }

    public Customer() {
        this(0, "None");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [" + id + "," + name + "]";
    }

}
