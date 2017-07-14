package com.example.james.rms.CommonProfile.Util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jamie on 14/7/2017.
 */

public class DeepCopyUtil {

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
