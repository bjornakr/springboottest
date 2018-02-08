package no.bjornakr.springboottest.get_respondent;

public class ResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String eMail;
    private String telephoneNumber;
    private String street1;
    private String street2;
    private String postalCode;
    private String city;
    private String country;

    public ResponseDto() { }

    public ResponseDto(
            Long id,
            String firstName,
            String lastName,
            String eMail,
            String telephoneNumber,
            String street1,
            String street2,
            String postalCode,
            String city,
            String country
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.telephoneNumber = telephoneNumber;
        this.street1 = street1;
        this.street2 = street2;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
