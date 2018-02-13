package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "motherboard")
public class MotherBoard extends SystemUnit {

    @Column
    private String socket;

    public MotherBoard() {
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }
}
