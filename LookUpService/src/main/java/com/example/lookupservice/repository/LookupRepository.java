package com.example.lookupservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookupRepository extends JpaRepository<Object, Long> {

    @Query(value = "select g.ref_key, g.ref_desc, g.ref_value from gender as g",nativeQuery = true)
    List<Object[]> genders();

    @Query(value = "select g.ref_key, g.ref_desc, g.ref_value from marital_status as g",nativeQuery = true)
    List<Object[]> maritalStatuses();

    List<Object[]> states();

    List<Object[]> lga(Long stateId);
}
