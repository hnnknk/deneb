package xyz.hnnknk.deneb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ram")
public class RAM extends SystemUnit {

    @Column
    private Integer capacity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ram")
    @JsonIgnore
    private Computer computer;

    public RAM() {
    }

    public RAM(String manufacturer, String model, Integer capacity) {
        setManufacturer(manufacturer);
        setModel(model);
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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
        if (!(o instanceof RAM)) return false;
        RAM ram = (RAM) o;
        return Objects.equals(getCapacity(), ram.getCapacity()) &&
            Objects.equals(getManufacturer(), ram.getManufacturer()) &&
                Objects.equals(getModel(), ram.getModel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCapacity());
    }

    @Override
    public String toString() {
        return "Ram [id=" + this.getId() + ", manufacturer=" + this.getManufacturer() + ", model=" + this.getModel()
                + ", capacity=" + this.getCapacity()  + "]";
    }
}
