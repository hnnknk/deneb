package xyz.hnnknk.deneb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hdd")
public class HDD extends SystemUnit {

    @Column
    private String serial;

    @Column
    private Integer capacity;

    @Column
    private String hddType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hdd")
    @JsonIgnore
    private Computer computer;

    public HDD(String manufacturer, String model, String serial, Integer capacity, String hddType) {
        setManufacturer(manufacturer);
        setModel(model);
        this.serial = serial;
        this.capacity = capacity;
        this.hddType = hddType;
    }

    public HDD() {}

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getHddType() {
        return hddType;
    }

    public void setHddType(String hddType) {
        this.hddType = hddType;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String toString() {
        return "Hdd [id=" + this.getId() + ", manufacturer=" + this.getManufacturer() + ", model=" + this.getModel()
                + ", serial number=" + this.getSerial() + ", capacity=" + this.getCapacity() + ", hddType=" + this.getHddType() + "]";
    }

    //Garbage equals, should be removed in next update
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HDD hdd = (HDD) o;
        return Objects.equals(getSerial(), hdd.getSerial()) &&
                Objects.equals(getCapacity(), hdd.getCapacity()) &&
                Objects.equals(getHddType(), hdd.getHddType()) &&
                Objects.equals(getManufacturer(), hdd.getManufacturer()) &&
                Objects.equals(getModel(), hdd.getModel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSerial(), getCapacity(), getHddType(), getModel(), getManufacturer());
    }
}
