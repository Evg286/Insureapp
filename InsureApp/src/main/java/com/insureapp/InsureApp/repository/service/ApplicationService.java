package com.insureapp.InsureApp.repository.service;
import com.insurapp.InsureApp.repository.model.Application;
import com.insurapp.InsureApp.repository.model.UserProfile;
import com.insurapp.InsureApp.repository.model.enums.EApplicationStatus;
import com.insurapp.InsureApp.repository.repository.ApplicationRepository;
import com.insurapp.InsureApp.repository.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileService userProfileService;


//    @Autowired
//    private PlansRepository plansRepository;

    public ApplicationService() {
        System.out.println("Application Service bean initialized!");;
    }

    /**
     *
     * @param apply
     * @return
     */



    public boolean saveApplicationDetails(Application apply){


        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        UserProfile userProfile = userProfileService.getUserProfile(session);

        apply.setUserId(userProfile.getUserId());
        apply.setName(userProfile.getName());
        apply.setEmail(userProfile.getEmail());
        apply.setPhone(userProfile.getPhone());
        apply.setStatus(EApplicationStatus.PENDING.toString());


        Application queryResults = applicationRepository.save(apply);

        return (queryResults  != null && queryResults.getApplicationId() > 0);

    }


    public List<Application> findApplicationsByStatus() {

        List<Application> applicationsList = applicationRepository.findByStatus(EApplicationStatus.PENDING.toString());

        return applicationsList;
    }

    public boolean updateApplicationStatus(int applicationId){

        Optional<Application> apply = applicationRepository.findById(applicationId);

        apply.ifPresent(applicationObject -> {
            applicationObject.setStatus(EApplicationStatus.APPROVED.toString());

        });
        Application queryResults = applicationRepository.save(apply.get());

        return (queryResults != null && queryResults.getUpdatedBy() != null);
    }
}
