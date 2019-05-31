package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Ping {

    @Id
    private String id;

    public Ping() {
        this.id = UUID.randomUUID().toString();
    }
}
