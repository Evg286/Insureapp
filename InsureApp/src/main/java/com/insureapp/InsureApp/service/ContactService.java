package com.insureapp.InsureApp.service;

import com.insurapp.InsureApp.model.Contact;
import com.insurapp.InsureApp.model.enums.EInquiryStatus;
import com.insurapp.InsureApp.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact Service bean initialized!");;
    }

    /**
     *
     * @param contact
     * @return
     */



    public boolean saveMessageDetails(Contact contact){


        contact.setStatus(EInquiryStatus.OPEN.toString());

        Contact queryResults = contactRepository.save(contact);

        return (queryResults  != null && queryResults.getInquiryId() > 0);

    }


    public List<Contact> findInquiriesByStatus() {

        List<Contact> inquiriesList = contactRepository.findByStatus(EInquiryStatus.OPEN.toString());

        return inquiriesList;
    }

    public boolean updateInquiryStatus(int inquiryId){

        Optional<Contact> contact = contactRepository.findById(inquiryId);

        contact.ifPresent(contactObject -> {
            contactObject.setStatus(EInquiryStatus.CLOSED.toString());

        });
        Contact queryResults = contactRepository.save(contact.get());

        return (queryResults != null && queryResults.getUpdatedBy() != null);
    }
}
