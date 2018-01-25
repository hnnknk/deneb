package xyz.hnnknk.deneb.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "monitor")
public class Monitor {

    @Id
    @GeneratedValue
    @Column(name = "monitor_id")
    private Long id;

    @Size(min = 1)
    @Column(name = "monitor_invnumber")
    private String invNumber;

    @Size(min = 2, max = 10)
    @Column(name = "monitor_manufacter")
    private String manufacter;

    @Size(min = 2, max = 10)
    @Column(name = "monitor_model")
    private String model;

    @Size(min = 2, max = 17)
    @Column(name = "monitor_serial")
    private String serial;

    public Monitor() {
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
        if (!(obj instanceof Monitor))
            return false;
        Monitor other = (Monitor) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Monitor [id=" + id + ", inventory number=" + invNumber + ", manufacter="
                + manufacter + ", model=" + model + ", serial number=" + serial + "]";
    }
}
