package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "monitor")
public class Monitor extends Peripheral{

    public Monitor() {
    }

    public Monitor (String invNumber, String manufacturer, String model, String serial) {
        super(invNumber, manufacturer, model, serial);
    }

    @Override
    public String toString() {
        return "Monitor [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufactururer="
                + this.getManufacturer() + ", model=" + this.getModel() + ", serial number=" + this.getSerial() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor mon = (Monitor) o;
        return Objects.equals(getInvNumber(), mon.getInvNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getInvNumber());
    }
}
