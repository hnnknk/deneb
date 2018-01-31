package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mouse")
public class Mouse {

    @Id
    @GeneratedValue
    @Column(name = "mouse_id")
    private Long id;

    @Size(min = 1)
    @Column(name = "mouse_invnumber")
    private String invNumber;

    @Size(min = 2, max = 10)
    @Column(name = "mouse_manufacter")
    private String manufacter;

    @Size(min = 2, max = 10)
    @Column(name = "mouse_model")
    private String model;

    @Size(min = 2, max = 17)
    @Column(name = "mouse_serial")
    private String serial;

    public Mouse() {
    }

    public Mouse(String invNumber, String manufacter, String model, String serial) {
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
        if (!(obj instanceof Mouse))
            return false;
        Mouse other = (Mouse) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Mouse [id=" + id + ", inventory number=" + invNumber + ", manufacter="
                + manufacter + ", model=" + model + ", serial number=" + serial + "]";
    }
}
