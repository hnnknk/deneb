package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ups")
public class Ups extends Peripheral{

    public Ups() {
    }

    public Ups (String invNumber, String manufacter, String model, String serial) {
        super(invNumber, manufacter, model, serial);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ups ups = (Ups) o;
        return Objects.equals(getInvNumber(), ups.getInvNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getInvNumber());
    }

    @Override
    public String toString() {
        return "Ups [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufacter="
                + this.getManufacter() + ", model=" + this.getModel() + ", serial number=" + this.getSerial() + "]";
    }
}
