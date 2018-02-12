package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "processor")
public class Processor {

    @Id
    @GeneratedValue
    @Column(name = "processor_id")
    private Long id;

    @Column(name = "processor_manufacter")
    private String manufacter;

    @Column(name = "processor_model")
    private String model;

    @Column(name = "processor_speed")
    private String speed;

    @Column(name = "processor_number_of_cores")
    private Integer numberOfCores;

    public Processor() {
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
