package xyz.borsay.RestAPI.Contact_list;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Contact {
    private @Id @GeneratedValue Long id;

    private String first;
    private String middle;
    private String last;

    private String street;
    private String streetSecond;
    private String city;
    private String state;
    private String zip;

    private String primaryPhone;
    private String primaryPhoneType;
    private String secondaryPhone;
    private String secondaryPhoneType;

    private String email ;

    /**
     * General Contact with no data entered
     */
    public Contact(){
        this.first = "";
        this.middle = "";
        this.last = "";

        this.street = "";
        this.streetSecond = "";
        this.city = "";
        this.state = "";
        this.zip = "";

        this.primaryPhone = "";
        this.primaryPhoneType = "";
        this.secondaryPhone = "";
        this.secondaryPhoneType = "";

        this.email = "";
    }

    /**
     * sets the three different parts of the name in the Contact.
     * @param first first name
     * @param middle middle name sad for people like me with two middle names ie: need to add that
     * @param last last name
     */
    private void setName(String first, String middle, String last){
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    /**
     * Sets the address for the person of the contacts
     * @param street full street address
     * @param streetSecond added to allow for apartment or unit numbers
     * @param city city
     * @param state could be for provinces and things like that as well
     * @param zip is also for Postal Code like they use in Canada
     */
    private void setAddress(String street, String streetSecond, String city, String state, String zip){
        this.street = street;
        this.streetSecond = streetSecond;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Adds the primary and secondary phone and there associated types.
     * types expecting are home|work|mobile you could have two mobile
     * @param primaryPhone primary
     * @param primaryPhoneType primary type
     * @param secondaryPhone secondary
     * @param secondaryPhoneType secondary type
     */
    private void setPhones(String primaryPhone, String primaryPhoneType,
                           String secondaryPhone, String secondaryPhoneType) {
        this.primaryPhone = primaryPhone;
        this.primaryPhoneType = primaryPhoneType;
        this.secondaryPhone = secondaryPhone;
        this.secondaryPhoneType = secondaryPhoneType;
    }

    /**
     * Adds all of the details for use in testing.
     * @param first first name
     * @param middle middle name
     * @param last last name
     * @param street numbered address
     * @param streetSecond apt or unit number
     * @param city your city
     * @param state your state or province
     * @param zip or postal code
     * @param primaryPhone primary
     * @param primaryPhoneType primary type
     * @param secondaryPhone secondary
     * @param secondaryPhoneType secondary type
     * @param email email address
     */
    public Contact(String first, String middle, String last, String street , String streetSecond, String city,
                   String state, String zip, String primaryPhone, String primaryPhoneType, String secondaryPhone,
                   String secondaryPhoneType, String email){
        setName(first, middle, last);
        setAddress( street,  "",  city,  state,  zip);
        setPhones(primaryPhone, primaryPhoneType, secondaryPhone, secondaryPhoneType);
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    /**
     * This probably will never be used but it is still sitting here in case
     * @param id generated id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetSecond() {
        return streetSecond;
    }

    /**
     * this is currently not set up in post or put but could be eventually added
     * @param streetSecond apt or unit #
     */
    public void setStreetSecond(String streetSecond) {
        this.streetSecond = streetSecond;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getPrimaryPhoneType() {
        return primaryPhoneType;
    }

    public void setPrimaryPhoneType(String primaryPhoneType) {
        this.primaryPhoneType = primaryPhoneType;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getSecondaryPhoneType() {
        return secondaryPhoneType;
    }

    public void setSecondaryPhoneType(String secondaryPhoneType) {
        this.secondaryPhoneType = secondaryPhoneType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
