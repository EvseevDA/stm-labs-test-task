package com.github.evseevda.businesslogicservice.core.cache.util;


import com.github.evseevda.businesslogicservice.user.entity.User;


public class CacheKey {

    private static final String TICKETS_POSTFIX = "#tickets";

    public static String tickets(User user) {
        return user + TICKETS_POSTFIX;
    }

}
