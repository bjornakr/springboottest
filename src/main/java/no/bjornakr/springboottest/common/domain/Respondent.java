package no.bjornakr.springboottest.common.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Respondent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private ContactInformation contactInformation;

//    private String firstName;
//    private String lastName;
//    private String eMail;
//    private String telephoneNumber;
//
//    @Embedded
//    private PostalAddress postalAddress;

//    public void setId(Long id) {
//        this.id = id;
//    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void seteMail(String eMail) {
//        this.eMail = eMail;
//    }
//
//    public void setTelephoneNumber(String telephoneNumber) {
//        this.telephoneNumber = telephoneNumber;
//    }
//
//    public void setPostalAddress(PostalAddress postalAddress) {
//        this.postalAddress = postalAddress;
//    }


    protected Respondent() {
        // Required by JPA - DO NOT USE!!!
    }

    //    @Autowired
    public Respondent(
            Name name,
            ContactInformation contactInformation
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(contactInformation);
        this.name = name;
        this.contactInformation = contactInformation;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }


//    private String trimRequiredString(String s, ErrorMessages errorMessage) {
//        String t = Trimmer.apply(s);
//        if (t == null) {
//            throw new EntityConstructionException(this.getClass(), errorMessage.toString());
//        } else {
//            return t;
//        }
//    }

//    // Taken from StackOverflow (Jason Buberel): https://stackoverflow.com/a/8204716
//    private boolean isEmailValid(String emailStr) {
//        Pattern VALID_EMAIL_ADDRESS_REGEX =
//                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
//                        Pattern.CASE_INSENSITIVE);
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
//        return matcher.find();
//    }
//
//    private String validateEmail(String eMail) {
//        String s = Trimmer.apply(eMail);
//        if (!isEmailValid(s)) {
//            String err = String.format("%s'%s'.", ErrorMessages.InvalidEmail, s);
//            throw new EntityConstructionException(this.getClass(), err);
//        }
//        return s;
//    }

}
