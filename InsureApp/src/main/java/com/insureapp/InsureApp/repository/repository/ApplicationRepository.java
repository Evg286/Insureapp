package com.insureapp.InsureApp.repository.repository;

import com.insurapp.InsureApp.repository.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findByStatus(String status);
//    List<Application> findByPlan(String status);

}
