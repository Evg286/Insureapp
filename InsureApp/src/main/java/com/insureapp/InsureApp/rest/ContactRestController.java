package com.insureapp.InsureApp.rest;

import com.insurapp.InsureApp.model.Contact;
import com.insurapp.InsureApp.model.CustomHttpRequest;
import com.insurapp.InsureApp.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api/inquiries")
@CrossOrigin(origins = "*")
public class ContactRestController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/getInquiriesByStatus")
    public List<Contact> getInquiriesByStatus(@RequestParam String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getInquiriesBySubject")
    public List<Contact> getInquiriesBySubject(@RequestBody Contact contact) {

        if (contact != null && contact.getMessage() != null)
            return contactRepository.findBySubjectContaining(contact.getMessage());
        else
            return Collections.emptyList();
    }

    @PostMapping("/saveInquiry")
    public ResponseEntity<Contact> saveInquiry(@Valid @RequestBody Contact contact) {

        Contact queryResponse = contactRepository.save(contact);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isInquirySaved", "true")
                .body(queryResponse);

    }

    @DeleteMapping("/deleteInquiry")
    public ResponseEntity<CustomHttpRequest> deleteInquiry(RequestEntity<Contact> requestEntity) {

        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getInquiryId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomHttpRequest(200, "Inquiry was deleted successfully!"));

    }



}
