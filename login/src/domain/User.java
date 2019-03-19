package domain;

import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String suffix;
    private String street;
    private String streetNumber;
    private String city;
    private String zipcode;
    private String phoneNumber;
    private String email;

    public User() {
    }
}