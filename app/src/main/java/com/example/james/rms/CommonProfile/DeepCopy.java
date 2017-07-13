package com.example.james.rms.CommonProfile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jamie on 14/7/2017.
 */

public class DeepCopy {

    public static LinkedHashMap<Long, Boolean> copyLinkedHashMap_Long_Boolean(LinkedHashMap<Long, Boolean> original)
    {
        LinkedHashMap<Long, Boolean> copy = new LinkedHashMap<>();
        for (Map.Entry<Long,Boolean> entry : original.entrySet())
        {
            copy.put(entry.getKey(),entry.getValue());
        }
        return copy;
    }
}
