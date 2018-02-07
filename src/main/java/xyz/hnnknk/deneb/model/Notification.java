package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "noti")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "noti_id")
    private long id;

    @Column(name = "email")
    private String email = "example@example.com";

    @Column(name = "noti_monitor")
    private boolean monitorCreated;

    @Column(name = "noti_ups")
    private boolean upsCreated;

    @Column(name = "noti_mouse")
    private boolean mouseCreated;

    @Column(name = "noti_keyboard")
    private boolean keyboardCreated;

    @Column(name = "noti_user")
    private boolean userCreated;


    public Notification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getMonitorCreated() {
        return monitorCreated;
    }

    public void setMonitorCreated(boolean monitorCreated) {
        this.monitorCreated = monitorCreated;
    }

    public boolean getMouseCreated() {
        return  this.mouseCreated;
    }

    public void  setMouseCreated(boolean mouseCreated) {
        this.mouseCreated = mouseCreated;
    }

    public boolean getKeyboardCreated() {
        return  this.keyboardCreated;
    }

    public void  setKeyboardCreated(boolean keyboardCreated) {
        this.keyboardCreated = keyboardCreated;
    }

    public boolean getUserCreated() {
        return  this.userCreated;
    }

    public void  setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }

    public boolean getUpsCreated() {
        return  this.upsCreated;
    }

    public void  setUpsCreated(boolean upsCreated) {
        this.upsCreated = upsCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
