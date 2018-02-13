package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "monitor")
public class Monitor extends Peripheral{

    public Monitor() {
    }

    public Monitor (String invNumber, String manufacter, String model, String serial) {
        super(invNumber, manufacter, model, serial);
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
        return this.getId().equals(other.getId());
    }

    @Override
    public String toString() {
        return "Monitor [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufacter="
                + this.getManufacter() + ", model=" + this.getModel() + ", serial number=" + this.getSerial() + "]";
    }
}
