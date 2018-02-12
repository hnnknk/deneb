package xyz.hnnknk.deneb.model;

import xyz.hnnknk.deneb.enums.HddTypes;

import javax.persistence.*;

@Entity
@Table(name = "hdd")
public class HDD {

    @Id
    @GeneratedValue
    @Column(name = "hdd_id")
    private Long id;

    @Column(name = "hdd_manufacter")
    private String manufacter;

    @Column(name = "hdd_model")
    private String model;

    @Column(name = "hdd_serial")
    private String serial;

    @Column(name = "hdd_capacity")
    private String capacity;

    @Column(name = "hdd_type")
    private HddTypes hddTypes;

    public HDD() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public HddTypes getHddTypes() {
        return hddTypes;
    }

    public void setHddTypes(HddTypes hddTypes) {
        this.hddTypes = hddTypes;
    }
}
