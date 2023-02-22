package com.example.lookupservice.controller;

import com.example.lookupservice.dtos.KeyValueAndAdditionalField;
import com.example.lookupservice.dtos.ResponseObject;
import com.example.lookupservice.service.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

import static com.example.lookupservice.resouce.KeyValuePairUtils.toKeyValueAndAdditionalField;

@RestController
@RequestMapping(LookupController.BASE_URL)
public class LookupController {
    public static final String BASE_URL = "/api/v1/lookup";

    private final Logger logger = LoggerFactory.getLogger(LookupController.class);


    private final LookupService lookupService;

    public LookupController(LookupService lookupService){
        this.lookupService = lookupService;
    }

    @GetMapping("/status")
    public String status(){
        return "Lookup Service is working";
    }

    @RequestMapping(method =  RequestMethod.GET, value = "genders")
    public ResponseObject<KeyValueAndAdditionalField> genders(){
        return cacheLookupThreeFields(LookupService::genders);
    }

    @RequestMapping(method =  RequestMethod.GET, value = "marital-status")
    public ResponseObject<KeyValueAndAdditionalField> maritalStatuses(){
        return cacheLookupThreeFields(LookupService::maritalStatuses);
    }

    protected ResponseObject<KeyValueAndAdditionalField> cacheLookupThreeFields(Function<LookupService, List<Object[]>> fxn) {
        ResponseObject<KeyValueAndAdditionalField> dataResponse = new ResponseObject<KeyValueAndAdditionalField>();
        try {
            dataResponse.setData(toKeyValueAndAdditionalField(fxn.apply(lookupService)));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            dataResponse.setValid(false);
            dataResponse.setMessage("Fetching Records did not perform as intended.");
        }
        return dataResponse;
    }
}
