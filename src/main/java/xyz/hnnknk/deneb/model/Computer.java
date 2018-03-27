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
    @JoinColumn(name = "com_id", referencedColumnName = "id")
    private HDD hdd;

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

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", invNumber='" + invNumber + '\'' +
                ", user='" + user + '\'' +
                ", location='" + location + '\'' +
                ", hdd=" + hdd +
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
