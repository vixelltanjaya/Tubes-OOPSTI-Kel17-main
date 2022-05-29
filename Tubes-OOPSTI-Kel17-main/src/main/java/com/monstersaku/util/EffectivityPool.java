package com.monstersaku.util;
import java.util.*;

public class EffectivityPool {
    
    public static HashMap<String, Double> effectMap = new HashMap<>();

    public static double getEffectivity(ElementType source, ElementType target){
        String key = source.toString() + "," + target.toString();
        // System.out.println(key);
        if(effectMap.get(key) == null) {
            return (1);
        }
        return effectMap.get(key);
    }

    public static void addEffectivity(ElementType source, ElementType target, double effectivity) {
        String key = source.toString() + "," + target.toString();
        effectMap.put(key, effectivity);
    }
    
}
