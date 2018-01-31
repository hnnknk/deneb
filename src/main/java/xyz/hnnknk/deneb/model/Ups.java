package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ups")
public class Ups {

    @Id
    @GeneratedValue
    @Column(name = "ups_id")
    private Long id;

    @Size(min = 1)
    @Column(name = "ups_invnumber")
    private String invNumber;

    @Size(min = 2, max = 10)
    @Column(name = "ups_manufacter")
    private String manufacter;

    @Size(min = 2, max = 10)
    @Column(name = "ups_model")
    private String model;

    @Size(min = 2, max = 17)
    @Column(name = "ups_serial")
    private String serial;

    public Ups() {
    }

    public Ups(String invNumber, String manufacter, String model, String serial) {
        this.invNumber = invNumber;
        this.manufacter = manufacter;
        this.model = model;
        this.serial = serial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Ups))
            return false;
        Ups other = (Ups) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ups [id=" + id + ", inventory number=" + invNumber + ", manufacter="
                + manufacter + ", model=" + model + ", serial number=" + serial + "]";
    }
}
