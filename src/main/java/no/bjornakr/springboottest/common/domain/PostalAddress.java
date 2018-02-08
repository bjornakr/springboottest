package no.bjornakr.springboottest.common.domain;

import com.sun.javaws.exceptions.InvalidArgumentException;
import no.bjornakr.springboottest.common.Trimmer;
import no.bjornakr.springboottest.common.error_handling.EntityConstructionException;

import javax.persistence.Embeddable;
import no.bjornakr.springboottest.common.error_handling.*;

@Embeddable
public class PostalAddress {
    private String street1;
    private String street2;
    private String postalCode;
    private String city;
    private String country;

    protected PostalAddress() {
        // Required by JPA
    }


    public PostalAddress(String street1, String street2, String postalCode, String city, String country) {
        this.street1 = Trimmer.apply(street1);
        this.street2 = Trimmer.apply(street2);
        this.postalCode = validatePostalCode(postalCode);
        this.city = Trimmer.apply(city);
        this.country = Trimmer.apply(country);
    }

    private String validatePostalCode(String postalCode) {
        String p = Trimmer.apply(postalCode);
        if (p != null && !p.matches("\\d*")) {
            String errorMessage = String.format("%s%s", ErrorMessages.InvalidPostalCode, p);
            throw new IllegalArgumentException(errorMessage);
        }
        return p;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
