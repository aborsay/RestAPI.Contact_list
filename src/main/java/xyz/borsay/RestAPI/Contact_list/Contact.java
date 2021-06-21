package xyz.borsay.RestAPI.Contact_list;


import com.fasterxml.jackson.databind.JsonNode;

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
    private void setName(String first, String middle, String last){
        this.first = first;
        this.middle = middle;
        this.last = last;
    }
    private void setAddress(String street, String streetSecond, String city, String state, String zip){
        this.street = street;
        this.streetSecond = streetSecond;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    private void setPhones(String primaryPhone, String primaryPhoneType,
                           String secondaryPhone, String secondaryPhoneType) {
        this.primaryPhone = primaryPhone;
        this.primaryPhoneType = primaryPhoneType;
        this.secondaryPhone = secondaryPhone;
        this.secondaryPhoneType = secondaryPhoneType;
    }
    public Contact(String first, String middle, String last, String street , String city, String state, String zip,
                   String primaryPhone, String primaryPhoneType, String secondaryPhone, String secondaryPhoneType){
        setName(first, middle, last);
        setAddress( street,  "",  city,  state,  zip);
        setPhones(primaryPhone, primaryPhoneType, secondaryPhone, secondaryPhoneType);
        this.email = "";
    }

    public Contact(String first, String middle, String last, String street , String city, String state, String zip){
        setName(first, middle, last);
        setAddress( street,  "",  city,  state,  zip);
        setPhones("", "","","");
        this.email = "";
    }

    public Contact(String first, String middle, String last){
        setName(first, middle, last);
    }

    public Long getId() {
        return id;
    }

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
