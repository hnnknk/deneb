package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@MappedSuperclass
public class SystemUnit {

    @Id
    @GeneratedValue
    private Long id;

    private String manufacter;

    private String model;

    public SystemUnit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacter() {
        return manufacter;
    }

    public void setManufacter(String manufacter) {
        this.manufacter = manufacter;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
