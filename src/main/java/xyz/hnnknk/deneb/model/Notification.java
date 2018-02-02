package xyz.hnnknk.deneb.model;

import javax.persistence.*;

@Entity
@Table(name = "note")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private long id;

    @Column(name = "note_user")
    private boolean isUserCreated = false;

    @Column(name = "note_monitor")
    private boolean isMonitorCreated = false;

    @Column(name = "note_mouse")
    private boolean isMouseCreated = false;

    @Column(name = "note_ups")
    private boolean isUpsCreated = false;

    @Column(name = "note_keyboard")
    private boolean isKeyboardCreated = false;

    public Notification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isUserCreated() {
        return isUserCreated;
    }

    public void setUserCreated(boolean userCreated) {
        isUserCreated = userCreated;
    }

    public boolean isMonitorCreated() {
        return isMonitorCreated;
    }

    public void setMonitorCreated(boolean monitorCreated) {
        isMonitorCreated = monitorCreated;
    }

    public boolean isMouseCreated() {
        return isMouseCreated;
    }

    public void setMouseCreated(boolean mouseCreated) {
        isMouseCreated = mouseCreated;
    }

    public boolean isUpsCreated() {
        return isUpsCreated;
    }

    public void setUpsCreated(boolean upsCreated) {
        isUpsCreated = upsCreated;
    }

    public boolean isKeyboardCreated() {
        return isKeyboardCreated;
    }

    public void setKeyboardCreated(boolean keyboardCreated) {
        isKeyboardCreated = keyboardCreated;
    }
}
