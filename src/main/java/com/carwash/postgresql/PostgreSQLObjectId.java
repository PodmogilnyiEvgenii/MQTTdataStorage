package com.carwash.postgresql;

import java.util.LinkedHashMap;
import java.util.Map;


public class PostgreSQLObjectId {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLWriteCashData.class);
    public static LinkedHashMap<String,Integer> carwashMap = new LinkedHashMap<>(); //carwash_num - carwash_id
    public static LinkedHashMap<Integer, Integer> ownerMap = new LinkedHashMap<>(); //carwash_id - owner_id

    public static void printCarwashMap(){
        logger.debug("carwashMap =============================");
        for (Map.Entry<String,Integer> entry : carwashMap.entrySet()) {
            logger.debug("{} = {}",entry.getKey(),entry.getValue());
        }
        logger.debug("========================================");
    }
    public static void printOwnerMap(){
        logger.debug("ownerMap =============================");
        for (Map.Entry<Integer, Integer> entry : ownerMap.entrySet()) {
            logger.debug("{} = {}",entry.getKey(),entry.getValue());
        }
        logger.debug("========================================");
    }


}
