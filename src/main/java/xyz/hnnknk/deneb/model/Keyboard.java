package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "keyboard")
public class Keyboard extends Peripheral{

    public Keyboard() {
    }

    public Keyboard(String invNumber, String manufacter, String model, String serial) {
        super(invNumber, manufacter, model, serial);
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
        return this.getId().equals(other.getId());
    }

    @Override
    public String toString() {
        return "Keyboard [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufacter="
                + this.getManufacter() + ", model=" + this.getModel() + ", serial number=" + this.getModel() + "]";
    }
}
