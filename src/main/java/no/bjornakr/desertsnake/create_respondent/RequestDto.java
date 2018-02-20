package no.bjornakr.desertsnake.create_respondent;

class RequestDto {
    private String firstName;
    private String lastName;
    private String eMail;
    private String telephoneNumber;
    private String streetAddress1;
    private String streetAddress2;
    private String postalCode;
    private String city;
    private String country;

    public RequestDto() {
    }

    public RequestDto(String firstName, String lastName, String eMail, String telephoneNumber, String streetAddress1, String streetAddress2, String postalCode, String city, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.telephoneNumber = telephoneNumber;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
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

    public String geteMail() {
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

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
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
