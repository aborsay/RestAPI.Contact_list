package xyz.borsay.RestAPI.Contact_list;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Right now this uses the correct Repository. JpaRepository is an extension of CrudRepository.
 * If standalone database were added not sure what changed would be needed here, if any.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
