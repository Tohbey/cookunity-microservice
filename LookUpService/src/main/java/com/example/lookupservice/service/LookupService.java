package com.example.lookupservice.service;

import com.example.lookupservice.repository.LookupRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupService {
    private final LookupRepository lookupRepository;

    public LookupService(LookupRepository lookupRepository){
        this.lookupRepository = lookupRepository;
    }

    @Cacheable(cacheNames = "genders")
    public List<Object[]> genders(){
        return lookupRepository.genders();
    }

    @Cacheable(cacheNames = "marital-status")
    public List<Object[]> maritalStatuses(){
        return lookupRepository.maritalStatuses();
    }
}
