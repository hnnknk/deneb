package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mouse")
public class Mouse extends Peripheral {

    public Mouse() {
    }

    public Mouse (String invNumber, String manufacturer, String model, String serial) {
        super(invNumber, manufacturer, model, serial);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return Objects.equals(getInvNumber(), mouse.getInvNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getInvNumber());
    }

    @Override
    public String toString() {
        return "Mouse [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufacturer="
                + this.getManufacturer() + ", model=" + this.getModel() + ", serial number=" + this.getSerial() + "]";
    }
}
