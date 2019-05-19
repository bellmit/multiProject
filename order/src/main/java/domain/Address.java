package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Address implements Serializable {
    @Id
    private String id;
    private String street;
    private String streetNr;
    private String city;
    private String zipcode;

    public Address() {
        this.id = UUID.randomUUID().toString();
    }

    public Address(String street, String streetNr, String city, String zipcode) {
        this();
        this.street = street;
        this.streetNr = streetNr;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getstreetNr() {
        return streetNr;
    }

    public void setstreetNr(String streetNr) {
        this.streetNr = streetNr;
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
}
