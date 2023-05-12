package com.insureapp.InsureApp.repository;

import com.insurapp.InsureApp.model.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepository extends CrudRepository<Plan, Integer> {


    List<Plan> getPlanByName(String name);
}
