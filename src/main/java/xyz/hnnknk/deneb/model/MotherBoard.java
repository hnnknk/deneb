package xyz.hnnknk.deneb.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "motherboard")
public class MotherBoard extends SystemUnit {

    @Column
    private String socket;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "motherBoard")
    private Computer computer;

    public MotherBoard() {
    }

    public MotherBoard(String manufacturer, String model, String socket) {
        setManufacturer(manufacturer);
        setModel(model);
        this.socket = socket;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    //Garbage equals, should be removed in next update
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MotherBoard)) return false;
        MotherBoard that = (MotherBoard) o;
        return Objects.equals(getSocket(), that.getSocket()) &&
                Objects.equals(getManufacturer(), that.getManufacturer()) &&
                Objects.equals(getModel(), that.getModel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSocket(), getManufacturer(), getModel());
    }

    @Override
    public String toString() {
        return "Motherboard [id=" + this.getId() + ", manufacturer=" + this.getManufacturer() + ", model=" + this.getModel()
                + ", socket=" + this.getSocket() + "]";
    }
}
