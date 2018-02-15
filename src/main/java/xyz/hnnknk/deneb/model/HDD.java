package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "hdd")
public class HDD extends SystemUnit {

    @Column
    private String serial;

    @Column
    private Integer capacity;

    @Column
    private String hddType;

    public HDD() {
    }

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

    @Override
    public String toString() {
        return "Hdd [id=" + this.getId() + ", manufacter=" + this.getManufacter() + ", model=" + this.getModel()
                + ", serial number=" + this.getSerial() + ", capacity=" + this.getCapacity() + ", hddType=" + this.getHddType() + "]";
    }
}
