package xyz.hnnknk.deneb.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Peripheral {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1)
    private String invNumber;

    @Size(min = 2, max = 10)
    private String manufacter;

    @Size(min = 2, max = 10)
    private String model;

    @Size(min = 2, max = 17)
    private String serial;

    public Peripheral() {
    }

    public Peripheral(String invNumber, String manufacter, String model, String serial) {
        this.setInvNumber(invNumber);
        this.setManufacter(manufacter);
        this.setModel(model);
        this.setSerial(serial);
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

    public void setManufacter(String manuacter) {
        this.manufacter = manuacter;
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
}
