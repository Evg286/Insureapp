package com.insureapp.InsureApp.repository;


import com.insurapp.InsureApp.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
//
    List<Contact> findBySubjectContaining(String subject);

    List<Contact> findByStatus(String status);

}
