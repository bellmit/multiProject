package domain;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Role {

    @Id
    private UUID id;
    private String role;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    public Role() {
        this.id = UUID.randomUUID();
    }
}
