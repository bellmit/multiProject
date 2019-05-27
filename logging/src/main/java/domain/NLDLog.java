package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Type;
import java.util.UUID;
import java.util.logging.Level;

@Entity
public class NLDLog {
    @Id
    private String uuid = UUID.randomUUID().toString();
    private Component component;
    private Class className;
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

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public NLDLog(Component component, Class className, String message, Level level) {
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
