package domain.dto;

import domain.User;
import util.RoleConverter;

import java.util.List;

public class UserDTO {
    private String id;
    private String email;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String suffix;
    private String street;
    private String streetNumber;
    private String city;
    private String zipcode;
    private String phoneNumber;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = RoleConverter.roleArrayToStringArray(user.getRoles());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.suffix = user.getSuffix();
        this.street = user.getStreet();
        this.streetNumber = user.getStreetNumber();
        this.city = user.getCity();
        this.zipcode = user.getZipcode();
        this.phoneNumber = user.getPhoneNumber();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
