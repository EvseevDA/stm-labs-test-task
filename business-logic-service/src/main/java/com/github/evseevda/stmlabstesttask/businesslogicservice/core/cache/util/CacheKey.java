package com.github.evseevda.stmlabstesttask.businesslogicservice.core.cache.util;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;


public class CacheKey {

    private static final String TICKETS_POSTFIX = "#tickets";

    public static String tickets(User user) {
        return user.getLogin() + TICKETS_POSTFIX;
    }

}
