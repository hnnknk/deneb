package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@MappedSuperclass
public class SystemUnit {

    @Id
    @GeneratedValue
    private Long id;

    private String manufacturer;

    private String model;

    public SystemUnit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
