package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "ram")
public class RAM extends SystemUnit{

    @Column
    private Integer capacity;

    public RAM() {
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
