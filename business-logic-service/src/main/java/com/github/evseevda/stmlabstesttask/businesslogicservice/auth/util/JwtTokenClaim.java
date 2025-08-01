package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.util;


public enum JwtTokenClaim {

    LOGIN("login"),
    ROLE("role");

    private final String name;

    JwtTokenClaim(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

}
