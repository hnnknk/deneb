package xyz.hnnknk.deneb.model;

import xyz.hnnknk.deneb.enums.HddTypes;

import javax.persistence.*;

@Entity
@Table(name = "hdd")
public class HDD extends SystemUnit {

    @Column
    private String serial;

    @Column
    private Integer capacity;

    @Column
    private HddTypes hddTypes;

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

    public HddTypes getHddTypes() {
        return hddTypes;
    }

    public void setHddTypes(HddTypes hddTypes) {
        this.hddTypes = hddTypes;
    }
}
