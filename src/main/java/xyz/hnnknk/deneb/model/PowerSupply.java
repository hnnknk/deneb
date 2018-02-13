package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "powersupply")
public class PowerSupply extends SystemUnit {

    @Column
    private Integer power;

    public PowerSupply() {
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
