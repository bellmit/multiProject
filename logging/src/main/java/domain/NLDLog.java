package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;
import java.util.logging.Level;

@Entity
public class NLDLog {
    @Id
    private String uuid = UUID.randomUUID().toString();
    private Component component;
    private String className;
    private String message;
    private Level level;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public NLDLog() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public NLDLog(Component component, String className, String message, Level level) {
        this.className = className;
        this.component = component;
        this.message = message;
        this.level = level;
    }

    public String getUuid() {
        return uuid;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
