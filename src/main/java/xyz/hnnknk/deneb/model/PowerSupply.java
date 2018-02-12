package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "powersupply")
public class PowerSupply {

    @Id
    @GeneratedValue
    @Column(name = "powersupply_id")
    private Long id;

    @Column(name = "powersupply_manufacter")
    private String manufacter;

    @Column(name = "powersupply_power")
    private Integer power;

    public PowerSupply() {
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

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
