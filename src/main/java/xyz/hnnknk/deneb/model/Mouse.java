package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "mouse")
public class Mouse extends Peripheral {

    public Mouse() {
    }

    public Mouse (String invNumber, String manufacter, String model, String serial) {
        super(invNumber, manufacter, model, serial);
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
        return this.getId().equals(other.getId());
    }

    @Override
    public String toString() {
        return "Mouse [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufacter="
                + this.getManufacter() + ", model=" + this.getModel() + ", serial number=" + this.getSerial() + "]";
    }
}
