package domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    @Id
    private UUID id;

    @Email
    @Column(unique = true)
    private String email;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String suffix;
    private String street;
    private String streetNumber;
    private String city;
    private String zipcode;
    private String phoneNumber;

    public User() {
        this.id = UUID.randomUUID();
    }
}