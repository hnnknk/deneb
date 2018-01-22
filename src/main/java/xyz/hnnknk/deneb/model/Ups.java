package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "ups")
public class Ups {

    @Id
    @GeneratedValue
    @Column(name = "ups_id")
    private Long id;

    @Column(name = "ups_invnumber")
    private int invNumber;

    @Column(name = "ups_manufacter")
    private String manufacter;

    @Column(name = "ups_model")
    private String model;

    @Column(name = "ups_serial")
    private String serial;

    public Ups() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(int invNumber) {
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
    public int hashCode() {
        final int prime = 35;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 36));
        return result;
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
