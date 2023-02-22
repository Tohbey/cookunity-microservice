package com.example.lookupservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyValueAndAdditionalField implements Serializable {
    private String key;
    private String value;
    private String code;

    public KeyValueAndAdditionalField(long key, String value, String code){
        this.key = String.valueOf(key);
        this.value = value;
        this.code = code;
    }
}
