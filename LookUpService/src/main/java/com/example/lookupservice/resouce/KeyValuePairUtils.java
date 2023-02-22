package com.example.lookupservice.resouce;

import com.example.lookupservice.dtos.KeyValueAndAdditionalField;

import java.util.ArrayList;
import java.util.List;

public class KeyValuePairUtils {

    public static List<KeyValueAndAdditionalField> toKeyValueAndAdditionalField(List<Object[]> objects){
        List<KeyValueAndAdditionalField> values = new ArrayList<>();
        if(objects != null){
            for(Object[] object: objects){
                KeyValueAndAdditionalField value  = new KeyValueAndAdditionalField();
                for(int i=0; i < (object == null ? 0: object.length); i++){
                    String key = object[i] != null ? object[i].toString() : null;;
                    if(i == 0){
                        value.setKey(key);
                    } else if (i == 1) {
                        value.setValue(returnDefaultValue(value.getValue(), key));
                    }else{
                        value.setCode(returnDefaultValue(value.getCode(), key));
                    }
                    values.add(value);
                }
            }
        }

        return values;
    }

    public static String returnDefaultValue(String value, String defaultValue) {
        return (value != null) ? String.format("%s-%s", value, defaultValue) : defaultValue;
    }
}
