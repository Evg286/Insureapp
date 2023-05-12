package com.insureapp.InsureApp.repository.repository;


import com.insurapp.InsureApp.repository.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
//
    List<Contact> findBySubjectContaining(String subject);

    List<Contact> findByStatus(String status);

}
