package xyz.hnnknk.deneb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "powersupply")
public class PowerSupply extends SystemUnit {

    @Column
    private Integer power;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "powerSupply")
    @JsonIgnore
    private Computer computer;

    public PowerSupply() {
    }

    public PowerSupply(String manufacturer, String model, Integer power) {
        setManufacturer(manufacturer);
        setModel(model);
        this.power = power;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    //Garbage equals, should be removed in next update
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerSupply)) return false;
        PowerSupply that = (PowerSupply) o;
        return Objects.equals(getPower(), that.getPower()) &&
                Objects.equals(getManufacturer(), that.getManufacturer()) &&
                Objects.equals(getModel(), that.getModel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPower(), getModel(), getManufacturer());
    }

    @Override
    public String toString() {
        return "PowerSupply [id=" + this.getId() + ", manufacturer=" + this.getManufacturer() + ", model=" + this.getModel()
                + ", power=" + this.getPower()  + "]";
    }
}
