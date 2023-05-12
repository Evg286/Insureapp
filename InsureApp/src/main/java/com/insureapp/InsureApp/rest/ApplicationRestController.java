package com.insureapp.InsureApp.rest;

import com.insurapp.InsureApp.model.Application;
import com.insurapp.InsureApp.model.CustomHttpRequest;
import com.insurapp.InsureApp.repository.ApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping(path = "/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationRestController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/getApplicationByStatus")
    public List<Application> getApplicationByStatus(@RequestParam String status) {
        return applicationRepository.findByStatus(status);
    }



    @PostMapping("/saveApplication")
    public ResponseEntity<Application> saveApplication(@Valid @RequestBody Application apply) {

        Application queryResponse = applicationRepository.save(apply);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isRequestSaved", "true")
                .body(queryResponse);

    }

    @DeleteMapping("/deleteApplication")
    public ResponseEntity<CustomHttpRequest> deleteApplication(RequestEntity<Application> requestEntity) {

        Application apply = requestEntity.getBody();
        applicationRepository.deleteById(apply.getApplicationId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomHttpRequest(200, "Application was deleted successfully!"));

    }



}

