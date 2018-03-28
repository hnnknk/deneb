package xyz.hnnknk.deneb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Computer {

    @Id
    @GeneratedValue
    @Column(name = "computer_id")
    private Long id;

    @Column(name = "computer_invnumber")
    private String invNumber;

    @Column(name = "computer_user")
    private String user;

    @Column(name = "computer_location")
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_hdd_id", referencedColumnName = "id")
    private HDD hdd;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_mother_id", referencedColumnName = "id")
    private MotherBoard motherBoard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_proc_id", referencedColumnName = "id")
    private Processor processor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_power_id", referencedColumnName = "id")
    private PowerSupply powerSupply;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_ram_id", referencedColumnName = "id")
    private RAM ram;

    public Computer() {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HDD getHdd() {
        return hdd;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", invNumber='" + invNumber + '\'' +
                ", user='" + user + '\'' +
                ", location='" + location + '\'' +
                ", hdd=" + hdd +
                ", motherBoard=" + motherBoard +
                ", processor=" + processor +
                ", powerSupply=" + powerSupply +
                ", ram=" + ram +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer)) return false;
        Computer computer = (Computer) o;
        return Objects.equals(getInvNumber(), computer.getInvNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getInvNumber());
    }
}
