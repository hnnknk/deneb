package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "keyboard")
public class Keyboard {

    @Id
    @GeneratedValue
    @Column(name = "keyboard_id")
    private Long id;

    @NotNull
    @Column(name = "keyboard_invnumber")
    private int invNumber;

    @Size(min = 2, max = 10)
    @Column(name = "keyboard_manufacter")
    private String manufacter;

    @Size(min = 2, max = 10)
    @Column(name = "keyboard_model")
    private String model;

    @Size(min = 2, max = 17)
    @Column(name = "keyboard_serial")
    private String serial;

    public Keyboard() {
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
        final int prime = 33;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 34));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Keyboard))
            return false;
        Keyboard other = (Keyboard) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Keyboard [id=" + id + ", inventory number=" + invNumber + ", manufacter="
                + manufacter + ", model=" + model + ", serial number=" + serial + "]";
    }
}
