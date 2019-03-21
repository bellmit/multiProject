package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")})
public class Role {

    @Id
    private UUID id;
    private String name;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    public Role() {
        this.id = UUID.randomUUID();
    }
}
