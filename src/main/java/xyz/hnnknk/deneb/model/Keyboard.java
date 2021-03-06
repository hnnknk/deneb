package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "keyboard")
public class Keyboard extends Peripheral {

    public Keyboard() {
    }

    public Keyboard(String invNumber, String manufacturer, String model, String serial) {
        super(invNumber, manufacturer, model, serial);
    }

    @Override
    public String toString() {
        return "Keyboard [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufacturer="
                + this.getManufacturer() + ", model=" + this.getModel() + ", serial number=" + this.getModel() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyboard key = (Keyboard) o;
        return Objects.equals(getInvNumber(), key.getInvNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getInvNumber());
    }
}
