package com.example.james.rms.CommonProfile;

/**
 * Created by james on 5/3/2017.
 */

public class ObjectType {

    public static Long stringToLong(String data){
        Long result = null;
        if(ObjectUtil.isNotNullEmpty(data)){
            if(ObjectUtil.isLongValue(data))result = new Long(data);
        }
       return result;
    }
}
