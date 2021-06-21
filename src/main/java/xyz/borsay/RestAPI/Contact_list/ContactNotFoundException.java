package xyz.borsay.RestAPI.Contact_list;

/**
 * used if someone puts an id that does not exist in the API
 */
public class ContactNotFoundException extends  RuntimeException{
    ContactNotFoundException(Long id){
        super("Could not find contact: "+ id);
    }
}
