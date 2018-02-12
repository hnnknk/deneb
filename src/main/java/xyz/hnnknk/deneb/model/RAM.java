package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "ram")
public class RAM {

    @Id
    @GeneratedValue
    @Column(name = "ram_id")
    private Long id;

    @Column(name = "ram_manufacter")
    private String manufacter;

    @Column(name = "ram_capacity")
    private Integer capacity;

    public RAM() {
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
