package xyz.borsay.RestAPI.Contact_list;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class ContactController {

    private  final ContactRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();
    private final String[] allowedPhoneType = new String[]{"home","mobile","work"};

    ContactController(ContactRepository repository){
        this.repository = repository;
    }

    /**
     * This Mapping is to get all of the contacts in JSON
     * @return String of all contacts
     */
    @GetMapping("/contacts")
    String all() {
        ArrayNode contactsAllArray = mapper.createArrayNode();
        List<Contact> contactsAll = repository.findAll();

        contactsAll.forEach((contact) -> contactsAllArray.add(getContactObject(contact)));

        return contactsAllArray.toString();
    }

    /**
     * Gets the call list if the person has a home number. If they have two home numbers it will show both numbers
     * @return list of contacts with home phone number
     */
    @GetMapping("/contacts/call-list")
    String callList(){
        ArrayNode contactsAllArray = mapper.createArrayNode();
        List<Contact> contactsAll = repository.findAll();
        contactsAll.forEach((contact) ->{
            if(contact.getPrimaryPhoneType().equals("home") || contact.getSecondaryPhoneType().equals("home")) {
                contactsAllArray.add(getCallListObjects(contact));
            }
        });

        return contactsAllArray.toString();
    }

    /**
     * takes the okay JSON contactNode and transforms it into the Contact
     * @param contactNode JsonNode from inputted data
     * @return Contact which has just been added
     */
    private Contact addNewContact(JsonNode contactNode){

        Contact newContact = new Contact();

        //makes sure that there is at the very least a first and last name before adding
        if(!contactNode.has("name")
                || (contactNode.has("name")
                && !contactNode.get("name").has("first") && !contactNode.get("name").has("last"))){
            return null;
        }

        //Since it is assuming from the test before that it has to have a first and last those will not be checked
        //again in the three below.
        newContact.setFirst(contactNode.get("name").get("first").asText());
        newContact.setMiddle(
                contactNode.get("name").has("middle") ? contactNode.get("name").get("middle").asText() : "");
        newContact.setLast(contactNode.get("name").get("last").asText());

        //This is just in-case the json does not have the address section
        newContact.setStreet("");
        newContact.setCity("");
        newContact.setState("");
        newContact.setZip("");

        //if address section found then test each subsection to insert otherwise leave blank
        if(contactNode.has("address")){
            newContact.setStreet(contactNode.get("address").has("street")
                    ? contactNode.get("address").get("street").asText() : "");
            newContact.setStreet(contactNode.get("address").has("streetSecond")
                    ? contactNode.get("address").get("streetSecond").asText() : "");
            newContact.setCity(contactNode.get("address").has("city")
                    ? contactNode.get("address").get("city").asText() : "");
            newContact.setState(contactNode.get("address").has("state")
                    ? contactNode.get("address").get("state").asText() : "");
            newContact.setZip(contactNode.get("address").has("zip")
                    ? contactNode.get("address").get("zip").asText() : "");
        }

        //This is just in-case the json does not have the phone section
        newContact.setPrimaryPhone("");
        newContact.setPrimaryPhoneType("");
        newContact.setSecondaryPhone("");
        newContact.setSecondaryPhoneType("");

        //if phone section found then test each subsection to insert otherwise leave blank
        if(contactNode.has("phone")){
            String primaryPhoneType = contactNode.get("phone").get(0).get("type").asText();
            newContact.setPrimaryPhone(contactNode.get("phone").get(0).get("number").asText());
            newContact.setPrimaryPhoneType("home");
            if(Arrays.asList(allowedPhoneType).contains(primaryPhoneType)){
                newContact.setPrimaryPhoneType(contactNode.get("phone").get(0).get("type").asText());
            }
            String secondaryPhoneType = contactNode.get("phone").get(1).get("type").asText();
            newContact.setSecondaryPhone(contactNode.get("phone").get(1).get("number").asText());
            newContact.setSecondaryPhoneType("home");
            if(Arrays.asList(allowedPhoneType).contains(secondaryPhoneType)){
                newContact.setSecondaryPhoneType(contactNode.get("phone").get(1).get("type").asText());
            }
        }

        //leaves blank if no email in JSON
        newContact.setEmail(contactNode.has("email") ? contactNode.get("email").asText() : "");


        return newContact;
    }

    /**
     * This adds a new contact to the repository
     * @param newContactString what should be a JSON string of new contact
     * @return the contact of the newly added contact
     */
    @PostMapping("/contacts")
    Contact newContact(@RequestBody String newContactString) {
        //Makes sure it is a JSON string
        if(isJSONValid(newContactString)){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode contactNode = null;
            try {
                contactNode = mapper.readTree(newContactString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            assert contactNode != null;
            //add to repository the new contact
            return repository.save(Objects.requireNonNull(addNewContact(contactNode)));
        }
        return null;
    }


    /**
     * Ensures that the JSON string is valid.
     * @param jsonInString String of inputted JSON format
     * @return boolean if it is valid JSON
     */
    private static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * This maps for when Get of individual contact based upon its id number
     * @param id contact id
     * @return JSON string in correct format
     */
    @GetMapping("/contacts/{id}")
    String one(@PathVariable Long id) {
        Contact contact = repository.findById(id) //
                .orElseThrow(() -> new ContactNotFoundException(id));

        ObjectNode contactObject = getContactObject(contact);

        return contactObject.toString();
    }



    /**
     * This calls and return contacts and only includes the phone number if it is of type home
     * @param contact individual contact from repository
     * @return contact info
     */
    private ObjectNode getCallListObjects(Contact contact){
        ObjectNode contactObject = mapper.createObjectNode();

        contactObject.set("name", getContactName(contact));

        if(contact.getPrimaryPhoneType().equals("home")){
            contactObject.put("phone", contact.getPrimaryPhone());

        }
        if(contact.getSecondaryPhoneType().equals("home")){
            contactObject.put("phone secondary", contact.getSecondaryPhone());

        }

        return contactObject;
    }

    /**
     * Returns name node from a contact
     * @param contact individual contact
     * @return return  name as ObjectNode
     */
    private ObjectNode getContactName(Contact contact){
        ObjectNode name = mapper.createObjectNode();
        name.put("first", contact.getFirst()).put("middle", contact.getMiddle())
                .put("last", contact.getLast());
        return name;
    }

    /**
     * Return address node from contact
     * @param contact individual contact
     * @return return  address as ObjectNode
     */
    private ObjectNode getContactAddress(Contact contact){
        ObjectNode address = mapper.createObjectNode();
        address.put("street", contact.getStreet()).put("city",contact.getCity())
                .put("state",contact.getState()).put("zip",contact.getZip());
        return address;
    }

    /**
     * This function creates the individual ObjectNode for each contact.
     * @param contact this is an individual contact
     * @return ObjectNode to make string in correct JSON form
     */
    private ObjectNode getContactObject(Contact contact){
        ObjectNode contactObject = mapper.createObjectNode();

        contactObject.put("id",contact.getId());

        contactObject.set("name", getContactName(contact));

        contactObject.set("address",getContactAddress(contact));

        ObjectNode primaryPhone = mapper.createObjectNode();
        primaryPhone.put("number",contact.getPrimaryPhone())
                .put("type",contact.getPrimaryPhoneType());

        ObjectNode secondaryPhone = mapper.createObjectNode();
        secondaryPhone.put("number",contact.getSecondaryPhone())
                .put("type",contact.getSecondaryPhoneType());

        ArrayNode phoneArray = mapper.createArrayNode();
        phoneArray.addAll(Arrays.asList(primaryPhone,secondaryPhone));
        contactObject.set("phone",phoneArray);

        contactObject.put("email",contact.getEmail());
        return contactObject;
    }

    @PutMapping("/contacts/{id}")
    Contact replaceContact(@RequestBody String newContactString, @PathVariable Long id) {
        if(isJSONValid(newContactString)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode contactNode = null;
            try {
                contactNode = mapper.readTree(newContactString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            assert contactNode != null;
            return addNewContact(contactNode);
        }
        return null;

        /*
        return repository.findById(id)
                .map(Contact -> {
                    // Contact.setName(newContact.getName());
                    // Contact.setRole(newContact.getRole());
                    return repository.save(Contact);
                })
                .orElseGet(() -> {
                    newContact.setId(id);
                    return repository.save(newContact);
                });
        }

         */
    }

    @DeleteMapping("/contacts/{id}")
    void deleteContact(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
