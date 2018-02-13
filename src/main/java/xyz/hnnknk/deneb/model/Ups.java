package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "ups")
public class Ups extends Peripheral{

    public Ups() {
    }

    public Ups (String invNumber, String manufacter, String model, String serial) {
        super(invNumber, manufacter, model, serial);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Ups))
            return false;
        Ups other = (Ups) obj;
        return this.getId().equals(other.getId());
    }

    @Override
    public String toString() {
        return "Ups [id=" + this.getId() + ", inventory number=" + this.getInvNumber() + ", manufacter="
                + this.getManufacter() + ", model=" + this.getModel() + ", serial number=" + this.getSerial() + "]";
    }
}
