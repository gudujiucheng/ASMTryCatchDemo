package com.addtrycatch;

import java.util.List;
import java.util.Map;


public class AddTryCatchExtension {

    public Map<String, Map<String,Object>> hookPoint;

    public Map<String, String> exceptionHandler;

    @Override
    public String toString() {
        return "AddTryCatchExtension{" +
                "hookPoint=" + hookPoint +
                ", exceptionHandler=" + exceptionHandler +
                '}';
    }
}
