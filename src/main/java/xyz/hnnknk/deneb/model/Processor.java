package xyz.hnnknk.deneb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "processor")
public class Processor extends SystemUnit{

    @Column
    private String speed;

    @Column
    private Integer numberOfCores;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "processor")
    @JsonIgnore
    private Computer computer;

    public Processor() {
    }

    public Processor(String manufacturer, String model, String speed, Integer numberOfCores) {
        setManufacturer(manufacturer);
        setModel(model);
        this.speed = speed;
        this.numberOfCores = numberOfCores;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Integer getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(Integer numberOfCores) {
        this.numberOfCores = numberOfCores;
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
        if (!(o instanceof Processor)) return false;
        Processor processor = (Processor) o;
        return Objects.equals(getSpeed(), processor.getSpeed()) &&
                Objects.equals(getModel(), processor.getModel()) &&
                Objects.equals(getManufacturer(), processor.getManufacturer()) &&
                Objects.equals(getNumberOfCores(), processor.getNumberOfCores());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSpeed(), getNumberOfCores(), getModel(), getManufacturer());
    }

    @Override
    public String toString() {
        return "Processor [id=" + this.getId() + ", manufacturer=" + this.getManufacturer() + ", model=" + this.getModel()
                + ", speed=" + this.getSpeed() + ", number of cores=" + this.getNumberOfCores()  + "]";
    }
}
