package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "processor")
public class Processor extends SystemUnit{

    @Column
    private String speed;

    @Column
    private Integer numberOfCores;

    public Processor() {
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Integer getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(Integer numberOfCores) {
        this.numberOfCores = numberOfCores;
    }
}
