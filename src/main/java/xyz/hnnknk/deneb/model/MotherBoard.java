package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "motherboard")
public class MotherBoard {

    @Id
    @GeneratedValue
    @Column(name = "motherboard_id")
    private Long id;

    @Column(name = "motherboard_manufacter")
    private String manufacter;

    @Column(name = "motherboard_model")
    private String model;

    @Column(name = "motherboard_socket")
    private String socket;

    public MotherBoard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacter() {
        return manufacter;
    }

    public void setManufacter(String manufacter) {
        this.manufacter = manufacter;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }
}
